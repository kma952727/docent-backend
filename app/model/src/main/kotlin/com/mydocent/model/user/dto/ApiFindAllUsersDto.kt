package com.mydocent.model.user.dto

class ApiFindAllUsersDto {
    data class Response(
        val id: Int,
        val nickName: String
    )
}