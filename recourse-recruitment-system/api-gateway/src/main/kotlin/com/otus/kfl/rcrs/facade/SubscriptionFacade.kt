package com.otus.kfl.rcrs.facade

import com.otus.kfl.rcrs.config.annotation.Facade
import com.otus.kfl.rcrs.dto.request.SubscribeRequest
import com.otus.kfl.rcrs.dto.response.SubscribeResponse
import com.otus.kfl.rcrs.mapper.toResponse
import com.otus.kfl.rcrs.usecase.SubscribeUseCase
import org.springframework.transaction.annotation.Transactional

@Facade
class SubscriptionFacade(
    private val subscribeUseCase: SubscribeUseCase
) {

    @Transactional
    fun subscribeUseCase(request: SubscribeRequest): SubscribeResponse {
        return subscribeUseCase.subscribe(request).toResponse()
    }
}