package com.mydocent.model.user.dto

import io.swagger.v3.oas.annotations.media.Schema

class ApiFindMyInfoDto {
    @Schema(description = "자신의 정보")
    data class Response(
        @Schema(description = "사용자 pk", requiredMode = Schema.RequiredMode.REQUIRED)
        val id: Int,
        @Schema(description = "사용자 닉네임", requiredMode = Schema.RequiredMode.REQUIRED)
        val nickname: String
    )
}