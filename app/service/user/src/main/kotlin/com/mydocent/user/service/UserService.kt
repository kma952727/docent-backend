package com.mydocent.user.service

import com.mydocent.model.user.dto.CreateUserApiDto
import com.mydocent.model.user.dto.FindAllUsersApiDto
import com.mydocent.model.user.entity.User
import com.mydocent.model.user.enum.OAuth2Type
import com.mydocent.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    fun createUser(createUserApiDto: CreateUserApiDto.Request): CreateUserApiDto.Response {
        val user = User.signUp(
            oauthId = 999,
            nickname = "test-${createUserApiDto.nickName}",
            email = "test@gmail.com",
            oAuth2Type = OAuth2Type.KAKAO
        )
        userRepository.save(user)
        return CreateUserApiDto.Response(userId = user.id!!)
    }

    fun findAllUsers(): List<FindAllUsersApiDto.Response> {
        return userRepository.findAll().map { FindAllUsersApiDto.Response(id = it.id!!, nickName = it.nickname) }
    }
}