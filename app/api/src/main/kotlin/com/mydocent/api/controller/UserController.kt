package com.mydocent.api.controller

import com.mydocent.model.user.dto.ApiCreateUserDto
import com.mydocent.model.user.dto.ApiFindAllUsersDto
import com.mydocent.user.service.AuthenticationService
import com.mydocent.utils.api.ApiResponse
import io.github.oshai.kotlinlogging.KotlinLogging
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@Tag(name = "UserController (테스트용 API)")
@RestController
@RequestMapping("/api")
class UserController(private val authenticationService: AuthenticationService) {
    private val log = KotlinLogging.logger { }

    /**
     * test용 API입니다.
     */
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    fun findAllUsers(): ApiResponse<List<ApiFindAllUsersDto.Response>> {
        log.info { "${::findAllUsers.name} ::" }
        return ApiResponse(data=authenticationService.findAllUsers())
    }

    /**
     * test용 API입니다.
     */
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    fun createUsers(@RequestBody apiCreateUserDto: ApiCreateUserDto.Request): ApiResponse<ApiCreateUserDto.Response> {
        log.info { "${::createUsers.name} :: ${apiCreateUserDto::class.simpleName} :: $apiCreateUserDto" }
        return ApiResponse(data=authenticationService.createUser(apiCreateUserDto))
    }
}