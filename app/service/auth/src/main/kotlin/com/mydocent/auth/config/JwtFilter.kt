package com.mydocent.auth.config

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.GenericFilterBean


@Component
class JwtFilter(
    private val jwtService: JwtService
) : GenericFilterBean() {

    private val AUTHORIZATION_HEADER = "Authorization"
    private val BEARER = "Bearer "

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val httpServletRequest = request as HttpServletRequest

        val accessToken = resolveAccessToken(httpServletRequest)

        if (StringUtils.hasText(accessToken) && jwtService.validateJwt(jwt = accessToken!!)) {
            val auth = UsernamePasswordAuthenticationToken(jwtService.parseToUserToken(accessToken), accessToken, null)
            SecurityContextHolder.getContext().authentication = auth
        }
        chain.doFilter(request, response)
    }

    /**
     * access token 추출
     */
    private fun resolveAccessToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader(AUTHORIZATION_HEADER)
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER)) {
            return bearerToken.substring(7)
        }
        return null
    }

}