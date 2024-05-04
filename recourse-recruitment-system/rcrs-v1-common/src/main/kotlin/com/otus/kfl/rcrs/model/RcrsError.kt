package com.otus.kfl.rcrs.model

data class RcrsError(
    val code: String = "",
    val group: String = "",
    val message: String = "",
    val exception: Throwable? = null
)
