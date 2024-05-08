package com.otus.kfl.rcrs.repository

import com.otus.kfl.rcrs.domain.Subscription

interface SubscriptionRepository {

    fun save(subscription: Subscription): Subscription
}