package com.mydocent.api.controller

import com.mydocent.model.user.dto.ApiUpdateUserNicknameDto
import com.mydocent.user.service.UserManageService
import com.mydocent.utils.api.ApiResponse
import com.mydocent.utils.api.MyInfo
import io.github.oshai.kotlinlogging.KotlinLogging
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@Tag(name = "UserController (사용자에 관한 기능)")
@RestController
@RequestMapping("/api")
class UserController(private val userService: UserManageService) {
    private val log = KotlinLogging.logger { }


    @Operation(
        summary = "사용자 닉네임 수정",
        description = """
            사용자의 닉네임을 수정
            - 사용하려는 닉네임이 이미 존재하는 경우 에러(4xx)로 응답합니다.
        """
    )
    @PutMapping("/users/nickname")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateUserNickname(
        @RequestBody apiUpdateUserNickname: ApiUpdateUserNicknameDto.Request,
        @Schema(hidden = true) myInfo: MyInfo
    ): ApiResponse<Nothing> {
        log.info { "${::updateUserNickname.javaClass.simpleName} :: ${ApiUpdateUserNicknameDto.Request::class.simpleName} :: $apiUpdateUserNickname" }
        userService.updateNickname(requestDto = apiUpdateUserNickname, userId =  myInfo.id)
        return ApiResponse()
    }
}