package com.otus.kfl.rcrs.dto.request

import com.otus.kfl.rcrs.enums.SubscriptionCategory

data class SubscriberInfo(
    val city: String,
    val contact: String,
    val category: SubscriptionCategory
)
