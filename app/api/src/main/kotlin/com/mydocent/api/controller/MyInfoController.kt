package com.mydocent.api.controller

import com.mydocent.model.user.dto.ApiFindMyInfoDto
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

@Tag(name = "MyInfoController - 자신(사용자)의 정보를 관리 / 인증 필수")
@RequestMapping("/api")
@RestController
class MyInfoController(private val userManageService: UserManageService) {

    private val log = KotlinLogging.logger { }

    @Operation(
        summary = "자신의 닉네임 수정",
        description = """
            사용자의 닉네임을 수정
            - 사용하려는 닉네임이 이미 존재하는 경우 에러(4xx)로 응답합니다.
        """
    )
    @PutMapping("/my-info/nickname")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateUserNickname(
        @RequestBody apiUpdateUserNickname: ApiUpdateUserNicknameDto.Request,
        @Schema(hidden = true) myInfo: MyInfo
    ): ApiResponse<Nothing> {
        log.info { "${::updateUserNickname.javaClass.simpleName} :: ${ApiUpdateUserNicknameDto.Request::class.simpleName} :: $apiUpdateUserNickname" }
        userManageService.updateNickname(requestDto = apiUpdateUserNickname, userId =  myInfo.id)
        return ApiResponse()
    }

    @Operation(summary = "자신의 정보를 조회")
    @GetMapping("/my-info")
    @ResponseStatus(HttpStatus.OK)
    fun findMyInfo(@Schema(hidden = true) myInfo: MyInfo): ApiResponse<ApiFindMyInfoDto.Response> {
        log.info { "${::findMyInfo.javaClass.simpleName} :: ${MyInfo::class.simpleName} :: $myInfo" }
        return ApiResponse(data = userManageService.findMyInfo(userId = myInfo.id))
    }

    @Operation(summary = "회원 탈퇴")
    @DeleteMapping("/my-info/leave")
    @ResponseStatus(HttpStatus.OK)
    fun leave(@Schema(hidden = true) myInfo: MyInfo): ApiResponse<Nothing> {
        log.info { "${::leave.javaClass.simpleName} :: ${MyInfo::class.simpleName} :: $myInfo" }
        userManageService.leave(userId = myInfo.id)
        return ApiResponse()
    }
}