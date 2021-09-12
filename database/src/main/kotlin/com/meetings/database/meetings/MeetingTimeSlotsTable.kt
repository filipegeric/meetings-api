package com.meetings.database.meetings

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.`java-time`.datetime

internal object MeetingTimeSlots : IntIdTable() {
    val meetingId = reference("meetingId", Meetings.id)
    val dateTime = datetime("dateTime")
}