package com.mydocent.user.service

import com.mydocent.user.repository.UserTokenRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthenticationService(
    private val userTokenRepository: UserTokenRepository
) {

    @Transactional
    fun logout(userId: Int) {
        userTokenRepository.findByUserId(userId)?.let(userTokenRepository::delete)
    }
}