package com.mydocent.utils.api

data class ApiResponse<T>(
    val data: T? = null,
    val message: String? = null
)
