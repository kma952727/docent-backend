package com.mydocent.user.repository

import com.mydocent.model.user.entity.UserToken
import org.springframework.data.jpa.repository.JpaRepository

interface UserJpaTokenRepository: JpaRepository<UserToken, Int> {
    fun findByUserId(userId: Int): UserToken?
}