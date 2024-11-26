package com.mydocent.shared.api

data class ApiResponse<T>(
    val data: T? = null,
    val message: String? = null
)
