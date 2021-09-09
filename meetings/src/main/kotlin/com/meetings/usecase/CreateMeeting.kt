package com.meetings.usecase

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.meetings.common.Actor
import com.meetings.common.BadRequest
import com.meetings.common.isValidEmail
import com.meetings.domain.Invitation
import com.meetings.domain.Meeting
import com.meetings.domain.TimeSlot
import com.meetings.dto.MeetingDto
import com.meetings.dto.toDto
import com.meetings.port.IdGenerator
import com.meetings.port.MeetingsRepository
import java.time.ZonedDateTime

data class CreateMeetingRequest(
    val title: String,
    val description: String,
    val timeSlots: List<String>,
    val invitations: List<String>
)
typealias CreateMeetingResponse = Result<MeetingDto, Throwable>

class CreateMeeting(
    private val repository: MeetingsRepository, private val idGenerator: IdGenerator
) {
    fun execute(request: CreateMeetingRequest, actor: Actor): CreateMeetingResponse {
        val error = validate(request)
        if (error != null) {
            return Err(error)
        }

        val meeting = createMeetingFrom(request, actor)
        repository.save(meeting)

        // TODO: send emails to invitations

        return Ok(meeting.toDto())
    }

    private fun validate(request: CreateMeetingRequest): Throwable? {
        val (title, description, timeSlots, invitations) = request

        if (title.isEmpty()) {
            return BadRequest("Title can't be empty")
        }
        if (description.isEmpty()) {
            return BadRequest("Description can't be empty")
        }
        if (timeSlots.isEmpty()) {
            return BadRequest("Time slots can't be empty")
        }
        for (slot in timeSlots) {
            try {
                ZonedDateTime.parse(slot)
            } catch (_: Throwable) {
                return BadRequest("$slot isn't a valid ISO8601 zoned date time")
            }
        }
        if (invitations.isEmpty()) {
            return BadRequest("Invitations can't be empty")
        }
        for (email in invitations) {
            if (!email.isValidEmail()) {
                return BadRequest("$email is not a valid email address")
            }
        }

        return null
    }

    private fun createMeetingFrom(request: CreateMeetingRequest, actor: Actor): Meeting {
        val meetingId = idGenerator.generate()
        val (title, description, timeSlots, invitations) = request

        return Meeting(
            meetingId,
            title,
            description,
            timeSlots.map { TimeSlot(ZonedDateTime.parse(it)) },
            invitations.map { Invitation(it) },
            actor,
        )
    }
}