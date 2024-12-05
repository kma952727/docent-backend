package com.mydocent.user.repository

import com.mydocent.model.user.entity.User
import com.mydocent.utils.error.DocentException
import com.mydocent.utils.error.ErrorCode
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull

interface UserRepository: JpaRepository<User, Int> {
    fun findAllByNickname(nickname: String): List<User>
}

fun UserRepository.findOrThrow(pk: Int): User {
    return this.findByIdOrNull(pk) ?: throw DocentException(ErrorCode.NOT_FOUND_USER)
}