package com.mydocent.model.user.dto

class FindAllUsersApiDto {
    data class Response(
        val id: Int,
        val nickName: String
    )
}