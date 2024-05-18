package com.otus.kfl.rcrs.model

data class FilterInnerObj(
    var dateTime: String = "",
    var props: MutableList<String> = mutableListOf(),
    var city: String = "",
    var platform: InnerPlatform = InnerPlatform.NONE
)
