package com.meetings.api.routing.meetings

import com.meetings.MeetingsFacade
import com.meetings.adapter.UuidGenerator
import com.meetings.database.Database
import io.ktor.routing.*

fun Routing.configureMeetingsRouting(database: Database) = route("/meetings") {
    val meetingsFacade = MeetingsFacade(database.meetingsRepository, UuidGenerator)

    createMeetingRoute(meetingsFacade::createMeeting)
}


