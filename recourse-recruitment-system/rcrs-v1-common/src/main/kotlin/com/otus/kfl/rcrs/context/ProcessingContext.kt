package com.otus.kfl.rcrs.context

import com.otus.kfl.rcrs.constant.NONE
import com.otus.kfl.rcrs.model.BaseInnerReq
import com.otus.kfl.rcrs.model.BaseInnerResp
import com.otus.kfl.rcrs.model.DebugMode
import com.otus.kfl.rcrs.model.DebugStub
import com.otus.kfl.rcrs.model.RcrsCommand
import com.otus.kfl.rcrs.model.RcrsError
import com.otus.kfl.rcrs.model.RcrsState
import kotlinx.datetime.Instant

data class ProcessingContext(
    var command: RcrsCommand = RcrsCommand.NONE,
    var state: RcrsState = RcrsState.NONE,
    val errors: MutableList<RcrsError> = mutableListOf(),
    var workMode: DebugMode = DebugMode.NONE,
    var stubCase: DebugStub = DebugStub.NONE,
    var timeStart: Instant = Instant.NONE,
    var headers: MutableMap<String, String> = mutableMapOf(),
    var request: BaseInnerReq? = null,
    var response: BaseInnerResp? = null
)
