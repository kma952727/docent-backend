package com.mydocent.auth.repository

import com.mydocent.model.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface AuthUserRepository: JpaRepository<User, Int> {
    fun findByIdAndDeletedAtIsNull(pk: Int): User?
}