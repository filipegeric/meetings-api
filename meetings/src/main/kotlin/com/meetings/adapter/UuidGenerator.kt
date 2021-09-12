package com.meetings.adapter

import com.meetings.port.IdGenerator
import java.util.UUID

object UuidGenerator : IdGenerator {
    override fun generate(): String = UUID.randomUUID().toString()
}