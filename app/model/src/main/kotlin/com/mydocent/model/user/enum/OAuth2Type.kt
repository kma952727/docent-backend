package com.mydocent.model.user.enum

enum class OAuth2Type(val registrationName: String, val code: Int) {
    KAKAO("kakao", 0);

    companion object {
        fun of(code: Int): OAuth2Type = entries.find { it.code == code } ?: throw IllegalArgumentException("로그인 타입이 정상적이지 않습니다.")
    }
}