package com.otus.kfl.rcrs.dto.request

data class SubscribeRequest(
    val system: SystemInfo,
    val subscriber: SubscriberInfo
)
