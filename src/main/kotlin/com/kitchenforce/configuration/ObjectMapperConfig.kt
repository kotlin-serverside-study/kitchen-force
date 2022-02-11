package com.kitchenforce.configuration

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Configuration
class ObjectMapperConfig {
    @Bean
    @Primary
    fun commonObjectMapper(): ObjectMapper {
        val jacksonObjectMapper = ObjectMapper()
        jacksonObjectMapper.registerModule(Jdk8Module())
        jacksonObjectMapper.registerModule(customTimeSerializer())
        jacksonObjectMapper.registerKotlinModule()
        jacksonObjectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL)
        return jacksonObjectMapper
    }

    private fun customTimeSerializer(): JavaTimeModule {
        val javaTimeModule = JavaTimeModule()
        javaTimeModule
            .addSerializer(
                LocalDate::class.java,
                LocalDateSerializer(
                    DateTimeFormatter.ISO_DATE
                )
            )
            .addSerializer(
                LocalDateTime::class.java,
                LocalDateTimeSerializer(
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
                )
            )
        return javaTimeModule
    }
}
