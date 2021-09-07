package com.meetings.users

import com.meetings.users.adapter.MemoryUsersRepository
import com.meetings.users.usecase.CreateUserRequest
import com.meetings.users.usecase.CreateUserResponse
import com.meetings.users.usecase.GetUserRequest
import com.meetings.users.usecase.UserDto
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CreateUserTests {
    private lateinit var usersFacade: UsersFacade

    @BeforeEach
    fun init() {
        usersFacade = UsersFacade(MemoryUsersRepository())
    }

    @Test
    fun `returns created user`() {
        val request = CreateUserRequest("an-id", "example@mail.com")

        val actual = usersFacade.createUser(request)

        val expected = CreateUserResponse(UserDto("an-id", "example@mail.com"))
        assertEquals(expected, actual)
    }

    @Test
    fun `enables getting user by id`() {
        val request = CreateUserRequest("an-id", "example@mail.com")

        usersFacade.createUser(request)

        val expected = UserDto("an-id", "example@mail.com")
        val actual = usersFacade.getUser(GetUserRequest("an-id"))
        assertEquals(expected, actual)
    }
}