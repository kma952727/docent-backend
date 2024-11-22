package com.mydocent.model.common

data class ApiResponse<T>(
    val data: T? = null,
    val message: String? = null
)
