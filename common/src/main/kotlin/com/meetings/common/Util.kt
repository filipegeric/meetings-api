package com.meetings.common

import java.time.ZonedDateTime
import java.util.regex.Pattern

fun String.isValidEmail(): Boolean {
    val pattern = Pattern.compile(".+@.+\\.[a-z]+")
    return pattern.matcher(this).matches()
}

fun ZonedDateTime.toISOString(): String = toString() // TODO