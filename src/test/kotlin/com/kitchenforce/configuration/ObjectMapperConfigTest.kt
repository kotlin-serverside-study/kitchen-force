package com.kitchenforce.configuration

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDate
import java.time.LocalDateTime

@SpringBootTest
@ActiveProfiles("test")
class ObjectMapperConfigTest @Autowired constructor(
    val objectMapper: ObjectMapper
) {

    @Test
    @DisplayName("LocalDateTime serialization test")
    fun serializationTest() {
        val testDto = TestDto(1L, LocalDateTime.now(), LocalDate.now())
        assertDoesNotThrow {
            val jsonString = objectMapper.writeValueAsString(testDto)
            println("===> JsonString : $jsonString")
        }
    }

    @Test
    @DisplayName("LocalDateTime serialization test")
    fun deserializationTest() {
        // JSR-130 이슈는 역직렬화와는 관련이 없지만 균형을 위한 테스트 코드 추가.
        val jsonString = """
            {"id":1,"createdAt":"2022-01-10T22:07:45.860","createdDate":"2022-01-10"}
        """.trimIndent()

        assertDoesNotThrow {
            val testDto = objectMapper.readValue(jsonString, TestDto::class.java)
            println("===> testDto : ${testDto.createdDate} | ${testDto.createdAt}")
            assertEquals(LocalDateTime.of(2022, 1, 10, 22, 7, 45, 860000000), testDto.createdAt)
        }
    }
}

open class TestDto(
    @get:JsonProperty("id")
    open var id: Long? = null,
    @get:JsonProperty("createdAt")
    open var createdAt: LocalDateTime? = null,
    @get:JsonProperty("createdDate")
    open var createdDate: LocalDate? = null
)
