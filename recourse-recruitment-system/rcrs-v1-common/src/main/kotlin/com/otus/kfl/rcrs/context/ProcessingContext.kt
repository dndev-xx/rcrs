package com.otus.kfl.rcrs.context

import com.otus.kfl.rcrs.api.v1.models.BaseRequest
import com.otus.kfl.rcrs.api.v1.models.BaseResponse
import com.otus.kfl.rcrs.api.v1.models.FilterObject
import com.otus.kfl.rcrs.api.v1.models.RequestDebugMode
import com.otus.kfl.rcrs.api.v1.models.RequestDebugStub
import com.otus.kfl.rcrs.constant.NONE
import com.otus.kfl.rcrs.model.RcrsCommand
import com.otus.kfl.rcrs.model.RcrsError
import com.otus.kfl.rcrs.model.RcrsState
import kotlinx.datetime.Instant

data class ProcessingContext(
    var command: RcrsCommand = RcrsCommand.NONE,
    var state: RcrsState = RcrsState.NONE,
    val errors: MutableList<RcrsError> = mutableListOf(),
    var workMode: RequestDebugMode = RequestDebugMode.NONE,
    var stubCase: RequestDebugStub = RequestDebugStub.NONE,
    var timeStart: Instant = Instant.NONE,
    var request: BaseRequest? = null,
    var filtered: FilterObject = FilterObject(),
    var response: BaseResponse? = null
)
