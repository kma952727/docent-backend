package com.mydocent.auth.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.mydocent.utils.api.ApiResponse
import com.mydocent.utils.error.DocentException
import com.mydocent.utils.error.ErrorCode
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
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException?
    ) {
        response.apply {
            characterEncoding = Charsets.UTF_8.name()
            status = HttpStatus.UNAUTHORIZED.value()
            contentType = MediaType.APPLICATION_JSON_VALUE
            writer.write(mapper.writeValueAsString(ApiResponse<Nothing>(message = "인증에 실패하였습니다.")))
        }
    }
}