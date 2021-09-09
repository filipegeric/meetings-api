import com.github.michaelbull.result.unwrap
import com.meetings.MeetingsFacade
import com.meetings.adapter.MemoryMeetingsRepository
import com.meetings.adapter.UuidGenerator
import com.meetings.common.Actor
import com.meetings.common.BadRequest
import com.meetings.dto.MeetingDto
import com.meetings.usecase.CreateMeetingRequest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CreateMeetingTests {
    private lateinit var meetingsFacade: MeetingsFacade
    private val actor = Actor("an-id", "an-email")

    @BeforeEach
    fun init() {
        meetingsFacade = MeetingsFacade(MemoryMeetingsRepository(), UuidGenerator())
    }

    @Test
    fun `returns proper error if title is empty`() {
        val request = CreateMeetingRequest(
            "", "", emptyList(), emptyList(),
        )

        val (result, error) = meetingsFacade.createMeeting(request, actor)

        assertNull(result)
        assertEquals(BadRequest("Title can't be empty"), error)
    }

    @Test
    fun `returns proper error if description is empty`() {
        val request = CreateMeetingRequest(
            "Title", "", emptyList(), emptyList(),
        )

        val (result, error) = meetingsFacade.createMeeting(request, actor)

        assertNull(result)
        assertEquals(BadRequest("Description can't be empty"), error)
    }

    @Test
    fun `returns proper error if timeSlots is empty list`() {
        val request = CreateMeetingRequest(
            "Title", "Desc", emptyList(), emptyList(),
        )

        val (result, error) = meetingsFacade.createMeeting(request, actor)

        assertNull(result)
        assertEquals(BadRequest("Time slots can't be empty"), error)
    }

    @Test
    fun `returns proper error if timeSlots contains invalid elements`() {
        val request = CreateMeetingRequest(
            "Title", "Desc", listOf("invalid-date"), emptyList(),
        )

        val (result, error) = meetingsFacade.createMeeting(request, actor)

        assertNull(result)
        assertEquals(BadRequest("invalid-date isn't a valid ISO8601 zoned date time"), error)
    }

    @Test
    fun `returns proper error if invitations is empty list`() {
        val request = CreateMeetingRequest(
            "Title", "Desc", listOf("2020-01-01T12:00:00Z"), emptyList(),
        )

        val (result, error) = meetingsFacade.createMeeting(request, actor)

        assertNull(result)
        assertEquals(BadRequest("Invitations can't be empty"), error)
    }

    @Test
    fun `returns proper error if invitations contains invalid emails`() {
        val request = CreateMeetingRequest(
            "Title", "Desc", listOf("2020-01-01T12:00:00Z"), listOf("valid@mail.com", "invalid"),
        )

        val (result, error) = meetingsFacade.createMeeting(request, actor)

        assertNull(result)
        assertEquals(BadRequest("invalid is not a valid email address"), error)
    }

    @Test
    fun `returns created meeting`() {
        val request = CreateMeetingRequest(
            "Title", "Desc", listOf("2020-01-01T12:00:00+03:00"), listOf("valid@mail.com")
        )

        val result = meetingsFacade.createMeeting(request, actor)
        val meetingDto = result.unwrap()

        assertEquals(
            MeetingDto(
                meetingDto.id,
                "Title",
                "Desc",
                listOf("2020-01-01T12:00+03:00"),
                listOf("valid@mail.com"),
                actor
            ), meetingDto
        )
    }

}