package com.otus.kfl.rcrs.dto.response.error

data class ErrorResponse(
    val code: Int,
    val field: String,
    val group: String,
    val message: String
)
