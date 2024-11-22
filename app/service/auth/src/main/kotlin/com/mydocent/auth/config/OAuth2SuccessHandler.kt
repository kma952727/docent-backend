package com.mydocent.auth.config

import com.mydocent.auth.repository.UserTokenRepository
import com.mydocent.model.oauth.UserAuthentication
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
    private val userTokenRepository: UserTokenRepository
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
        userTokenRepository.save(userToken)

        makeResponse(response = response, accessToken = accessToken, refreshToken = refreshToken)
    }

    private fun makeResponse(response: HttpServletResponse, accessToken: String, refreshToken: String) {
        response.contentType = "application/json"
        response.characterEncoding = "utf-8"
        response.status = HttpStatus.OK.value()

        response.addCookie(Cookie("accessToken", accessToken))
        response.addCookie(Cookie("refreshToken", refreshToken))
    }
}