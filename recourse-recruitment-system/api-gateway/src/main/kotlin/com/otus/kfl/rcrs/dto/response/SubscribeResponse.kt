package com.otus.kfl.rcrs.dto.response

import com.otus.kfl.rcrs.dto.response.error.ErrorResponse

data class SubscribeResponse(
    val system: ResponseSystemInfo,
    val errors: List<ErrorResponse>? = null
)
