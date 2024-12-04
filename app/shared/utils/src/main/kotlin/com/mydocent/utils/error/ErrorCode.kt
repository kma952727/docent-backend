package com.mydocent.utils.error

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val code: String,
    val httpStatus: HttpStatus,
    val message: String
) {
    NOT_FOUND_USER(code = "not_found_user", httpStatus = HttpStatus.NOT_FOUND, message = "존재하지 않는 사용자"),
    INTERNAL_ERROR(code = "internal_error", httpStatus = HttpStatus.INTERNAL_SERVER_ERROR, message = "원인을 알 수 없는 에러");

    companion object {
        fun codeOf(code: String): ErrorCode = entries.first { it.code == code }
    }
}