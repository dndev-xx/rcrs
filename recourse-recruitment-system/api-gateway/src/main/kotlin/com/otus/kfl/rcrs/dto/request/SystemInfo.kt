package com.otus.kfl.rcrs.dto.request

import java.time.Instant
import java.util.*

data class SystemInfo(
    val rqUid: String? = UUID.randomUUID().toString(),
    val rqTime: Instant? = Instant.now(),
)
