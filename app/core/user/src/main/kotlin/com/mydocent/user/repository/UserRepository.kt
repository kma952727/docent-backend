package com.mydocent.user.repository

import com.mydocent.model.user.entity.User
import com.mydocent.utils.error.DocentException
import com.mydocent.utils.error.ErrorCode
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Int> {
    fun findAllByNickname(nickname: String): List<User>
    fun findByIdAndDeletedAtIsNull(pk: Int): User?
}

/**
 * 삭제 마킹처리 사용자는 제외하고 조회
 */
fun UserRepository.findOrThrow(pk: Int): User {
    return findByIdAndDeletedAtIsNull(pk) ?: throw DocentException(ErrorCode.NOT_FOUND_USER)
}