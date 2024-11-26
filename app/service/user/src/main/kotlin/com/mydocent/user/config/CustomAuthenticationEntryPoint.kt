package com.mydocent.user.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.mydocent.shared.api.ApiResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint

class CustomAuthenticationEntryPoint(
    private val mapper: ObjectMapper
): AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?
    ) {
        val responseBody = mapper.writeValueAsString(ApiResponse<Nothing>(message = "인증에 실패하였습니다."))
        response?.apply {
            this.characterEncoding = "UTF-8"
            status = HttpStatus.UNAUTHORIZED.value()
            contentType = MediaType.APPLICATION_JSON_VALUE
            writer.write(responseBody)
        }
    }
}