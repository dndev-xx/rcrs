package com.otus.kfl.rcrs.domain

import java.time.Instant
import java.util.UUID

data class User(
    val id: UUID? = null,
    val city: String,
    val contact: String,
    val createdAt: Instant? = null,
    val updatedAt: Instant? = null,
)