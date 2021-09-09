package com.meetings

import com.meetings.common.Actor
import com.meetings.port.IdGenerator
import com.meetings.port.MeetingsRepository
import com.meetings.usecase.CreateMeeting
import com.meetings.usecase.CreateMeetingRequest
import com.meetings.usecase.CreateMeetingResponse

class MeetingsFacade(repository: MeetingsRepository, idGenerator: IdGenerator) {
    private val createMeetingUseCase = CreateMeeting(repository, idGenerator)

    fun createMeeting(request: CreateMeetingRequest, actor: Actor): CreateMeetingResponse {
        return createMeetingUseCase.execute(request, actor)
    }
}