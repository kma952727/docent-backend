package com.mydocent.auth.config

import com.mydocent.auth.repository.AuthUserTokenRepository
import com.mydocent.model.auth.UserAuthentication
import com.mydocent.model.user.entity.UserToken
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.Date


@Component
class OAuth2SuccessHandler(
    private val jwtService: JwtService,
    private val authUserTokenRepository: AuthUserTokenRepository
): AuthenticationSuccessHandler {

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val user = (authentication.principal as PrincipalDetails).user

        val accessToken = jwtService.createJwt(
            UserAuthentication(email = user.email, expiration = Date.from(Instant.now().plus(6, ChronoUnit.HOURS)))
        )

        val refreshToken = jwtService.createJwt(
            UserAuthentication(email = user.email, expiration = Date.from(Instant.now().plus(6, ChronoUnit.DAYS)))
        )

        // refresh 저장로직 추가하기
        val userToken = UserToken(accessToken = accessToken, refreshToken = refreshToken)
        authUserTokenRepository.save(userToken)

        makeResponse(response = response, accessToken = accessToken, refreshToken = refreshToken)
    }

    private fun makeResponse(response: HttpServletResponse, accessToken: String, refreshToken: String) {
        response.contentType = "application/json"
        response.characterEncoding = "utf-8"
        response.status = HttpStatus.OK.value()

        /**
         * TODO: cookie 반환 방법
         * header나 cookie 어디로 반환할지 프론트와 이야기하기
         */
        response.setHeader("accessToken", accessToken)
        response.setHeader("refreshToken", refreshToken)

        response.addCookie(Cookie("accessToken", accessToken))
        response.addCookie(Cookie("refreshToken", refreshToken))
    }
}