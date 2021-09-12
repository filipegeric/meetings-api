package com.meetings.database.meetings

import com.meetings.domain.Meeting
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert

internal object Meetings : Table() {
    val id = varchar("id", 50)
    val title = varchar("title", 254)
    val description = text("description")
    val creatorId = varchar("creatorId", 50)
    val creatorEmail = varchar("creatorEmail", 254)

    fun insert(meeting: Meeting) = insert {
        it[id] = meeting.id
        it[title] = meeting.title
        it[description] = meeting.description
        it[creatorId] = meeting.createdBy.id
        it[creatorEmail] = meeting.createdBy.email
    }
}