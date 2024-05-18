package com.otus.kfl.rcrs.model

data class InnerReqObject(
    val email: String = "",
    val props: MutableList<String> = mutableListOf()
)
