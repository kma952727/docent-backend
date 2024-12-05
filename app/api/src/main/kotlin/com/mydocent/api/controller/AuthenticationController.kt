package com.mydocent.api.controller

import com.mydocent.model.user.dto.ApiFindAccessTokenDto
import com.mydocent.user.service.AuthenticationService
import com.mydocent.utils.api.ApiResponse
import com.mydocent.utils.api.MyInfo
import io.github.oshai.kotlinlogging.KotlinLogging
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@Tag(name = "AuthenticationController - 인증에 관한 기능")
@RequestMapping("/api")
@RestController
class AuthenticationController(
    private val authenticationService: AuthenticationService
) {
    private val log = KotlinLogging.logger { }

    @Operation(hidden = true)
    @GetMapping("/auth/access-token")
    fun findAccessToken(): ApiResponse<ApiFindAccessTokenDto.Response> {
        return ApiResponse(
            data = ApiFindAccessTokenDto.Response(accessToken = "im token")
        )
    }

    @Operation(
        summary = "사용자 로그아웃",
        description = """
            `docent server`에서 로그아웃 처리
            - `kakaoAuth server`는 로그아웃 처리하지 않습니다.
            - `header`에 `Access Token`만 보내면 됩니다.
            - `Token` 검증 과정때문에 에러를 반환할 수 있습니다. 이러한 경우 브라우저에서 token 정보를 버리면 로그아웃과 동일한 상태가 됩니다.
        """
    )
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/auth/logout")
    fun logout(@Schema(hidden = true) myInfo: MyInfo): ApiResponse<Nothing> {
        log.info { "${::logout::class.java.simpleName} :: $myInfo" }
        authenticationService.logout(userId = myInfo.id)
        return ApiResponse()
    }
}