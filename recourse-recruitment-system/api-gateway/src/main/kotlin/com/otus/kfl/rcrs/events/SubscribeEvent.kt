package com.otus.kfl.rcrs.events

import com.otus.kfl.rcrs.domain.Subscription
import com.otus.kfl.rcrs.dto.request.SystemInfo

class SubscribeEvent(
    override val system: SystemInfo,
    override val type: String = "Подписка на систему рекомендаций",
    val subscription: Subscription,
) : Event()