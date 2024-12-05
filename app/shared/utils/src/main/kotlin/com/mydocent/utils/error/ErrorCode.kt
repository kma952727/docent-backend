package com.mydocent.utils.error

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val code: String,
    val httpStatus: HttpStatus,
    val message: String
) {
    NOT_FOUND_USER(code = "not_found_user", httpStatus = HttpStatus.NOT_FOUND, message = "존재하지 않는 사용자"),
    NOT_FOUND_PLAY_LIST(code = "not_found_play_list", httpStatus = HttpStatus.NOT_FOUND, message = "존재하지 않는 플레이 리스트"),
    CONFLICT_USER_NICKNAME(code = "conflict_user_nickname", httpStatus = HttpStatus.CONFLICT, message = "중복된 사용자 닉네임"),
    INTERNAL_ERROR(code = "internal_error", httpStatus = HttpStatus.INTERNAL_SERVER_ERROR, message = "원인을 알 수 없는 에러");

    companion object {
        fun codeOf(code: String): ErrorCode = entries.first { it.code == code }
    }
}