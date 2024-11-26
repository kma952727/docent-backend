package com.mydocent.user.controller

import com.mydocent.shared.api.ApiResponse
import com.mydocent.model.user.dto.FindAccessTokenApiDto
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