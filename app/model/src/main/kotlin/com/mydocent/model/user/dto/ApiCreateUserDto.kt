package com.mydocent.model.user.dto

class ApiCreateUserDto {
    data class Request(val nickName: String)
    data class Response(val userId: Int)
}