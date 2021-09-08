package com.meetings.users

import com.github.michaelbull.result.unwrap
import com.meetings.users.adapter.MemoryUsersRepository
import com.meetings.users.usecase.CreateUserRequest
import com.meetings.users.usecase.GetUserRequest
import com.meetings.users.usecase.UserNotFound
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GetUserTests {
    private lateinit var usersFacade: UsersFacade

    @BeforeEach
    fun init() {
        usersFacade = UsersFacade(MemoryUsersRepository())
    }

    @Test
    fun `returns proper error if user is not previously created`() {
        val (userDto, error) = usersFacade.getUser(GetUserRequest("non-existing"))
        assertNull(userDto)
        assertEquals(UserNotFound("non-existing"), error)
    }

    @Test
    fun `returns previously created user`() {
        createUser("an-id", "example@mail.com")

        val result = usersFacade.getUser(GetUserRequest("an-id"))
        val userDto = result.unwrap()

        assertEquals(userDto.id, "an-id")
        assertEquals(userDto.email, "example@mail.com")
    }

    private fun createUser(id: String, email: String) {
        usersFacade.createUser(CreateUserRequest(id, email))
    }
}