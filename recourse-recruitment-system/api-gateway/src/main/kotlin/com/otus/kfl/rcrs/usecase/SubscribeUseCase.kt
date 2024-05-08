package com.otus.kfl.rcrs.usecase

import com.otus.kfl.rcrs.GatewayApplication.Companion.log
import com.otus.kfl.rcrs.config.annotation.UseCase
import com.otus.kfl.rcrs.domain.Subscription
import com.otus.kfl.rcrs.dto.request.SubscribeRequest
import com.otus.kfl.rcrs.events.EventSender
import com.otus.kfl.rcrs.mapper.toSubscribeEvent
import com.otus.kfl.rcrs.repository.SubscriptionRepository

@UseCase
class SubscribeUseCase(
    private val subscriptionRepository: SubscriptionRepository,
    private val eventSender: EventSender,
) {

    fun subscribe(request: SubscribeRequest): Subscription {
        log.info("Создаем событие подписки на систему, ID запроса: ${request.system.rqUid}")
        val event = request.toSubscribeEvent()
        eventSender.send(event)
        log.info("Сохраняем подписку нового пользователя в БД, ID запроса: ${event.system.rqUid}")
        val subscription = subscriptionRepository.save(event.subscription)
        log.info("Подписка нового пользователя с ID ${subscription.user?.id}, ID подписки: ${subscription.id}")
        return subscription
    }
}
