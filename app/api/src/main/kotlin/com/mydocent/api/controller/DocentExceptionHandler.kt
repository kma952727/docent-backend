package com.mydocent.api.controller

import com.mydocent.utils.api.ApiResponse
import com.mydocent.utils.error.DocentException
import com.mydocent.utils.error.ErrorCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class DocentExceptionHandler {

    @ExceptionHandler(DocentException::class)
    fun handleMyDocentException(ex: DocentException): ResponseEntity<ApiResponse<Nothing>> {
        val errorCode = ErrorCode.codeOf(ex.docentErrorCode.code)
        val body = ApiResponse<Nothing>(message = errorCode.message)

        return ResponseEntity.status(errorCode.httpStatus)
            .body(body)
    }

    @ExceptionHandler(*[IllegalArgumentException::class, IllegalStateException::class])
    fun handleIllegalException(ex: RuntimeException): ResponseEntity<ApiResponse<Nothing>> {
        val errorCode = ErrorCode.codeOf(ex.message!!)
        val body = ApiResponse<Nothing>(message = errorCode.message)

        return ResponseEntity.status(errorCode.httpStatus)
            .body(body)
    }


    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): ResponseEntity<ApiResponse<Nothing>> {
        val body = ApiResponse<Nothing>(message = ErrorCode.INTERNAL_ERROR.message)

        return ResponseEntity.status(ErrorCode.INTERNAL_ERROR.httpStatus)
            .body(body)
    }

}