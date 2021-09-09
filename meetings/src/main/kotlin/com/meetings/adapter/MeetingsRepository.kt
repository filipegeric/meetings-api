package com.meetings.adapter

import com.meetings.domain.Meeting
import com.meetings.port.MeetingsRepository

internal class MemoryMeetingsRepository: MeetingsRepository {
    private val meetings = mutableMapOf<String, Meeting>()

    override fun save(meeting: Meeting) {
        meetings[meeting.id] = meeting
    }
}