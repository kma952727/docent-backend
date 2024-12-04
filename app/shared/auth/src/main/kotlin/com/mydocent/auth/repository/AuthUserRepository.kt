package com.mydocent.auth.repository

import com.mydocent.model.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface AuthUserRepository: JpaRepository<User, Int>