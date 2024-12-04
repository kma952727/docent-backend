package com.mydocent.api.controller

import com.mydocent.model.user.dto.FindAccessTokenApiDto
import com.mydocent.utils.api.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/tokens")
@RestController
class JwtController {

    @GetMapping("/access-token")
    fun findAccessToken(): ApiResponse<FindAccessTokenApiDto.Response> {

        return ApiResponse(
            data = FindAccessTokenApiDto.Response(accessToken = "im token")
        )
    }
}