package com.mydocent.auth.controller

import com.mydocent.model.common.ApiResponse
import com.mydocent.model.user.dto.GetAccessTokenDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api")
@RestController
class JwtController {

    @GetMapping("/access-token")
    fun getAccessToken(): ApiResponse<GetAccessTokenDto.Response> {

        return ApiResponse(
            data = GetAccessTokenDto.Response(accessToken = "im token")
        )
    }
}