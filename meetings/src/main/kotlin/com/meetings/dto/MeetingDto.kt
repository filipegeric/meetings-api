package com.meetings.dto

import com.meetings.common.Actor
import com.meetings.common.toISOString
import com.meetings.domain.Meeting

data class MeetingDto(
    val id: String,
    val title: String,
    val description: String,
    val timeSlots: List<String>,
    val invitations: List<String>,
    val createdBy: Actor
)

fun Meeting.toDto(): MeetingDto = MeetingDto(
    id,
    title,
    description,
    timeSlots.map { it.dateTime.toISOString() },
    invitations.map { it.email },
    createdBy
)