package com.mydocent.user.service

import com.mydocent.model.auth.OAuth2Type
import com.mydocent.model.user.dto.ApiCreateUserDto
import com.mydocent.model.user.dto.ApiFindAllUsersDto
import com.mydocent.model.user.entity.User
import com.mydocent.user.repository.UserRepository
import com.mydocent.user.repository.UserTokenRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthenticationService(
    private val userRepository: UserRepository,
    private val userTokenRepository: UserTokenRepository
) {

    fun createUser(apiCreateUserDto: ApiCreateUserDto.Request): ApiCreateUserDto.Response {
        val user = User.signUp(
            oauthId = 999,
            nickname = "test-${apiCreateUserDto.nickName}",
            email = "test@gmail.com",
            oAuth2Type = OAuth2Type.KAKAO
        )
        userRepository.save(user)
        return ApiCreateUserDto.Response(userId = user.id!!)
    }

    fun findAllUsers(): List<ApiFindAllUsersDto.Response> {
        return userRepository.findAll().map { ApiFindAllUsersDto.Response(id = it.id!!, nickName = it.nickname) }
    }

    @Transactional
    fun logout(userId: Int) {
        userTokenRepository.findByUserId(userId)?.let(userTokenRepository::delete)
    }
}