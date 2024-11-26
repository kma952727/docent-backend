package com.mydocent.user.repository

import com.mydocent.model.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long>