package com.mydocent.user.repository

import com.mydocent.model.user.entity.UserToken
import org.springframework.data.jpa.repository.JpaRepository

interface UserTokenRepository: JpaRepository<UserToken, Int> {
    fun findByUserId(userId: Int): UserToken?
}