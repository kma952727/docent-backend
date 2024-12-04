package com.mydocent.auth.config

import com.mydocent.model.user.entity.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.core.user.OAuth2User


class PrincipalDetails(
    val user: User,
    private val attributes: MutableMap<String, Any>
): OAuth2User {

    override fun getName(): String = user.id.toString()

    override fun getAttributes(): MutableMap<String, Any> = attributes

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return ArrayList()
    }
}