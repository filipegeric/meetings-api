package com.meetings.database.meetings

import org.jetbrains.exposed.dao.id.IntIdTable

internal object MeetingInvitations: IntIdTable() {
    val meetingId = reference("meetingId", Meetings.id)
    val email = varchar("email", 254)
}