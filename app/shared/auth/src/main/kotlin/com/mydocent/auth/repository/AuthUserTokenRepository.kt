package com.mydocent.auth.repository

import com.mydocent.model.user.entity.UserToken
import org.springframework.data.jpa.repository.JpaRepository

interface AuthUserTokenRepository: JpaRepository<UserToken, Int>