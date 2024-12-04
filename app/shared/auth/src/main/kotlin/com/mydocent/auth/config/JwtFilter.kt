package com.mydocent.auth.config

import com.mydocent.auth.config.JwtService
import com.mydocent.auth.repository.AuthUserRepository
import com.mydocent.utils.api.MyInfo
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.GenericFilterBean


@Component
class JwtFilter(
    private val jwtService: JwtService,
    private val authUserRepository: AuthUserRepository
) : GenericFilterBean() {

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val httpServletRequest = request as HttpServletRequest

        val accessToken = resolveAccessToken(httpServletRequest)

        if (StringUtils.hasText(accessToken) && jwtService.validateJwt(jwt = accessToken!!)) {
            val userId = jwtService.parseToUserToken(accessToken).userId
            val user = authUserRepository.findByIdOrNull(userId)!!
            val myInfo = MyInfo(id = user.id!!)

            // TODO: 의존성 문제로 임시 처리
            request.setAttribute(MyInfo::class.java.simpleName, myInfo)

            val auth = UsernamePasswordAuthenticationToken(jwtService.parseToUserToken(accessToken), accessToken, null)
            SecurityContextHolder.getContext().authentication = auth
        }
        chain.doFilter(request, response)
    }

    /**
     * access token 추출
     */
    private fun resolveAccessToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7)
        }
        return null
    }

}