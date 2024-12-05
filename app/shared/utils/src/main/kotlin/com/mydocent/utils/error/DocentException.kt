package com.mydocent.utils.error

data class DocentException(val docentErrorCode: ErrorCode): RuntimeException(docentErrorCode.message)