package com.meetings.database.meetings

import com.meetings.domain.Meeting
import com.meetings.port.MeetingsRepository
import org.jetbrains.exposed.sql.batchInsert
import org.jetbrains.exposed.sql.transactions.transaction

class ExposedMeetingsRepository : MeetingsRepository {
    override fun save(meeting: Meeting) = transaction {
        Meetings.insert(meeting)
        MeetingTimeSlots.batchInsert(meeting.timeSlots) {
            this[MeetingTimeSlots.meetingId] = meeting.id
            this[MeetingTimeSlots.dateTime] = it.dateTime.toLocalDateTime()
        }
        MeetingInvitations.batchInsert(meeting.invitations) {
            this[MeetingInvitations.meetingId] = meeting.id
            this[MeetingInvitations.email] = it.email
        }
        return@transaction
    }
}





