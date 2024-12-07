package com.mydocent.user.service

import com.mydocent.user.repository.UserJpaTokenRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthenticationService(
    private val userJpaTokenRepository: UserJpaTokenRepository
) {

    @Transactional
    fun logout(userId: Int) {
        userJpaTokenRepository.findByUserId(userId)?.let(userJpaTokenRepository::delete)
    }
}