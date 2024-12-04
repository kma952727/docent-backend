package com.mydocent.utils.api

/**
 * 모든 `api`에서 해당 클래스로 래핑해서 응답한다.
 */
data class ApiResponse<T>(
    val data: T? = null,
    val message: String? = null
)
