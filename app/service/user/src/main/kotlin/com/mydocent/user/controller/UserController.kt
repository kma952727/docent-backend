package com.mydocent.user.controller

import com.mydocent.model.user.dto.CreateUserApiDto
import com.mydocent.shared.api.ApiResponse
import com.mydocent.model.user.dto.FindAllUsersApiDto
import com.mydocent.user.service.UserService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {
    private val log = KotlinLogging.logger { }

    /**
     * test용 API입니다.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun findAllUsers(): ApiResponse<List<FindAllUsersApiDto.Response>> {
        log.info { "${::findAllUsers.name} ::" }
        return ApiResponse(data=userService.findAllUsers())
    }

    /**
     * test용 API입니다.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createUsers(@RequestBody createUserApiDto: CreateUserApiDto.Request): ApiResponse<CreateUserApiDto.Response> {
        log.info { "${::createUsers.name} :: ${createUserApiDto::class.simpleName} :: $createUserApiDto" }
        return ApiResponse(data=userService.createUser(createUserApiDto))
    }
}