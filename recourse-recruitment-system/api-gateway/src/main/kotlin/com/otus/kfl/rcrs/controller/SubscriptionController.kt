package com.otus.kfl.rcrs.controller

import com.otus.kfl.rcrs.dto.request.SubscribeRequest
import com.otus.kfl.rcrs.dto.response.SubscribeResponse
import com.otus.kfl.rcrs.facade.SubscriptionFacade
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class SubscriptionController(
    private val subscriptionFacade: SubscriptionFacade,
) {

    @PostMapping("/subscribe")
    fun subscribe(
        @RequestHeader("x3-trace-id") traceId: String,
        @RequestHeader("x3-span-id") spanId: String,
        @RequestHeader("x3-sc-name") scName: String,
        @RequestHeader("x3-rq-type") rqType: String,
        @RequestHeader("x3-business-key", required = false) businessKey: String?,
        @RequestBody request: SubscribeRequest,
    ): SubscribeResponse {
        return subscriptionFacade.subscribeUseCase(request)
    }
}