package com.meetings.port

import com.meetings.domain.Meeting

interface MeetingsRepository {
    fun save(meeting: Meeting)
}