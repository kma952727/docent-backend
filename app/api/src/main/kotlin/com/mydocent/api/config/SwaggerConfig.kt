package com.mydocent.api.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun openAPI(): OpenAPI =
        Info().apply {
            version = "1.0.0"
            title = "Docent User"
        }
            .let { OpenAPI().info(it) }
}