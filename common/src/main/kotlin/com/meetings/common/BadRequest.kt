package com.meetings.common

data class BadRequest(override val message: String) : Throwable(message)
