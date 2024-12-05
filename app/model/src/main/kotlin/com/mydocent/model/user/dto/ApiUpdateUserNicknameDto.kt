package com.mydocent.model.user.dto

import io.swagger.v3.oas.annotations.media.Schema

class ApiUpdateUserNicknameDto {
    @Schema(description = "사용자 닉네임 수정 요청")
    data class Request(
        @Schema(description = "변경하고 싶은 새로운 닉네임", requiredMode = Schema.RequiredMode.REQUIRED)
        val newNickname: String
    )
}