package com.mydocent.model.auth.vo

import java.util.Date

/**
 * 1. Security Layer에서 사용
 * 2. 인증 완료된 사용자의 정보를 표현
 */
class UserAuthentication(
    val userId: Int,
    val email: String,
    val expiration: Date
)