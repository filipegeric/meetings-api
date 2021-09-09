package com.meetings.domain

import com.meetings.common.Actor
import java.time.ZonedDateTime

data class TimeSlot(val dateTime: ZonedDateTime)
data class Invitation(val email: String)

data class Meeting(
    val id: String,
    val title: String,
    val description: String,
    val timeSlots: List<TimeSlot>,
    val invitations: List<Invitation>,
    val createdBy: Actor,
)