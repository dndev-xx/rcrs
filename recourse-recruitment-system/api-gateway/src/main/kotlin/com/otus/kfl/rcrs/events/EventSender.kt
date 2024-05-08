package com.otus.kfl.rcrs.events

import com.otus.kfl.rcrs.GatewayApplication.Companion.log
import com.otus.kfl.rcrs.config.annotation.KafkaSender
import org.springframework.kafka.core.KafkaTemplate

@KafkaSender
class EventSender(
    private val kafkaTemplate: KafkaTemplate<String, Any>,
) {

    fun send(event: Event) {
        log.info("Начался процесс отправки события: '${event.type}', ID запроса: ${event.system.rqUid}")
        kafkaTemplate.send("builder-service", event)
        log.info("Событие '${event.type}', отправлено успешно, ID запроса: ${event.system.rqUid}")
    }
}