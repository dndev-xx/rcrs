package com.otus.kfl.rcrs.mapper

import com.otus.kfl.rcrs.domain.Subscription
import com.otus.kfl.rcrs.domain.User
import com.otus.kfl.rcrs.dto.request.SubscribeRequest
import com.otus.kfl.rcrs.dto.request.SubscriberInfo
import com.otus.kfl.rcrs.dto.response.ResponseSystemInfo
import com.otus.kfl.rcrs.dto.response.SubscribeResponse
import com.otus.kfl.rcrs.entity.SubscriptionJpaEntity
import com.otus.kfl.rcrs.entity.UserJpaEntity
import com.otus.kfl.rcrs.enums.SubscriptionStatus
import com.otus.kfl.rcrs.events.SubscribeEvent

fun SubscribeRequest.toSubscription(): Subscription {
    return Subscription(
        isActive = false,
        user = this.subscriber.toUser(),
        status = SubscriptionStatus.INIT,
        category = this.subscriber.category
    )
}

fun SubscriberInfo.toUser(): User {
    return User(
        city = this.city,
        contact = this.contact,
    )
}

fun SubscribeRequest.toSubscribeEvent(): SubscribeEvent {
    return SubscribeEvent(
        system = this.system,
        subscription = this.toSubscription()
    )
}

fun Subscription.toJpaEntity(): SubscriptionJpaEntity {
    return SubscriptionJpaEntity().apply {
        isActive = this@toJpaEntity.isActive
        status = this@toJpaEntity.status
        category = this@toJpaEntity.category
        user = this@toJpaEntity.user?.toJpaEntity()
    }
}

fun User.toJpaEntity(): UserJpaEntity {
    return UserJpaEntity().apply {
        city = this@toJpaEntity.city
        contact = this@toJpaEntity.contact
    }
}

fun SubscriptionJpaEntity.toSubscription(): Subscription {
    return Subscription(
        id = this.id,
        user = this.user!!.toUser(),
        isActive = this.isActive!!,
        status = this.status!!,
        category = this.category!!,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
    )
}

fun UserJpaEntity.toUser(): User {
    return User(
        id = this.id,
        city = this.city!!,
        contact = this.contact!!,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
    )
}

fun Subscription.toResponse(): SubscribeResponse {
    return SubscribeResponse(
        system = ResponseSystemInfo(responseType = "success", resultStatus = "$status"),
    )
}
