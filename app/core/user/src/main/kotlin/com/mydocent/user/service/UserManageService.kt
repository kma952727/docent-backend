package com.mydocent.user.service

import com.mydocent.model.user.dto.ApiFindMyInfoDto
import com.mydocent.model.user.dto.ApiUpdateUserNicknameDto
import com.mydocent.user.repository.UserJpaRepository
import com.mydocent.user.repository.findOrThrow
import com.mydocent.utils.error.ErrorCode
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class UserManageService(private val userRepository: UserJpaRepository) {

    @Transactional
    fun updateNickname(requestDto: ApiUpdateUserNicknameDto.Request, userId: Int) {
        val conflictUsers = userRepository.findAllByNickname(requestDto.newNickname)
        require(conflictUsers.isEmpty()) {
            ErrorCode.CONFLICT_USER_NICKNAME.code
        }

        val user = userRepository.findOrThrow(pk = userId)
        user.nickname = requestDto.newNickname
    }

    @Transactional
    fun leave(userId: Int) {
        userRepository.findOrThrow(pk = userId).leave(LocalDateTime.now())
    }

    fun findMyInfo(userId: Int): ApiFindMyInfoDto.Response {
        val user = userRepository.findOrThrow(pk = userId)
        return ApiFindMyInfoDto.Response(id = user.id!!, nickname = user.nickname)
    }


}