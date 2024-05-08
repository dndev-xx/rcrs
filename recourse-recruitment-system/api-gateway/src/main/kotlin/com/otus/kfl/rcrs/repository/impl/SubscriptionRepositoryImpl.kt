package com.otus.kfl.rcrs.repository.impl

import com.otus.kfl.rcrs.domain.Subscription
import com.otus.kfl.rcrs.mapper.toJpaEntity
import com.otus.kfl.rcrs.mapper.toSubscription
import com.otus.kfl.rcrs.repository.SubscriptionRepository
import com.otus.kfl.rcrs.repository.jpa.SubscriptionJpaRepository
import org.springframework.stereotype.Repository

@Repository
class SubscriptionRepositoryImpl(
    private val subscriptionJpaRepository: SubscriptionJpaRepository,
) : SubscriptionRepository {

    override fun save(subscription: Subscription): Subscription {
        val transientEntity = subscription.toJpaEntity()
        val persistedEntity = subscriptionJpaRepository.save(transientEntity)
        return persistedEntity.toSubscription()
    }
}