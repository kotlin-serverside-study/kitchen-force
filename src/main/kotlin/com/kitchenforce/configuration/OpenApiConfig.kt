package com.kitchenforce.configuration

import org.springdoc.core.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {
    @Bean
    public fun api(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("apis")
            .pathsToMatch("/api/**")
            .build()
    }
}
