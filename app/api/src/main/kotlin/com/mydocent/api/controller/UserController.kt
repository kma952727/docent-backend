package com.mydocent.api.controller

import com.mydocent.model.user.dto.CreateUserApiDto
import com.mydocent.model.user.dto.FindAllUsersApiDto
import com.mydocent.user.service.UserService
import com.mydocent.utils.api.ApiResponse
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

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