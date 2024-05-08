package com.otus.kfl.rcrs.domain

import com.otus.kfl.rcrs.enums.SubscriptionStatus
import com.otus.kfl.rcrs.enums.SubscriptionCategory
import java.time.Instant
import java.util.UUID

data class Subscription(
    val id: UUID? = null,
    val user: User? = null,
    val isActive: Boolean,
    val status: SubscriptionStatus,
    val category: SubscriptionCategory,
    val createdAt: Instant? = null,
    val updatedAt: Instant? = null,
)