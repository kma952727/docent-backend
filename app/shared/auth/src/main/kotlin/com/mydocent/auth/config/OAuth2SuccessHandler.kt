package com.mydocent.auth.config

import com.mydocent.auth.repository.AuthUserTokenRepository
import com.mydocent.model.auth.UserAuthentication
import com.mydocent.model.user.entity.User
import com.mydocent.model.user.entity.UserToken
import com.mydocent.utils.api.MyInfo
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
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
            UserAuthentication(userId = user.id!!, email = user.email, expiration = Date.from(Instant.now().plus(6, ChronoUnit.HOURS)))
        )

        val refreshToken = jwtService.createJwt(
            UserAuthentication(userId = user.id!!, email = user.email, expiration = Date.from(Instant.now().plus(6, ChronoUnit.DAYS)))
        )

        // refresh 저장로직 추가하기
        val userToken = UserToken(accessToken = accessToken, refreshToken = refreshToken, userId = user.id!!)
        authUserTokenRepository.save(userToken)

        makeRequest(user = user, request = request)
        makeResponse(response = response, accessToken = accessToken, refreshToken = refreshToken)
    }

    private fun makeRequest(user: User, request: HttpServletRequest) {
        val myInfo = MyInfo(id = user.id!!)
        request.setAttribute(MyInfo::class.java.simpleName, myInfo)
    }

    private fun makeResponse(response: HttpServletResponse, accessToken: String, refreshToken: String) {
        response.apply {
            contentType = MediaType.APPLICATION_JSON_VALUE
            characterEncoding = Charsets.UTF_8.name()
            status = HttpStatus.OK.value()

            setHeader("accessToken", accessToken)
            setHeader("refreshToken", refreshToken)

            addCookie(Cookie("accessToken", accessToken))
            addCookie(Cookie("refreshToken", refreshToken))
        }
    }
}