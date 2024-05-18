package com.otus.kfl.rcrs

import com.otus.kfl.rcrs.api.v1.models.BaseRequest
import com.otus.kfl.rcrs.api.v1.models.Platform
import com.otus.kfl.rcrs.api.v1.models.RequestDebugMode
import com.otus.kfl.rcrs.api.v1.models.RequestDebugStub
import com.otus.kfl.rcrs.api.v1.models.VcCommonRequest
import com.otus.kfl.rcrs.api.v1.models.VcReadRequest
import com.otus.kfl.rcrs.api.v1.models.VcStatRequest
import com.otus.kfl.rcrs.context.ProcessingContext
import com.otus.kfl.rcrs.exception.UnknownRequestClass
import com.otus.kfl.rcrs.model.DebugMode
import com.otus.kfl.rcrs.model.DebugStub
import com.otus.kfl.rcrs.model.FilterInnerObj
import com.otus.kfl.rcrs.model.FilterInnerStatObj
import com.otus.kfl.rcrs.model.InnerPlatform
import com.otus.kfl.rcrs.model.InnerReqObject
import com.otus.kfl.rcrs.model.InnerSystem
import com.otus.kfl.rcrs.model.RcrsCommand
import com.otus.kfl.rcrs.model.impl.InnerCommonReqImpl
import com.otus.kfl.rcrs.model.impl.InnerReadReqImpl
import com.otus.kfl.rcrs.model.impl.InnerStatReqImpl

fun ProcessingContext.toTransport(req: BaseRequest) = when(req) {
    is VcCommonRequest -> toTransport(req)
    is VcReadRequest -> toTransport(req)
    is VcStatRequest -> toTransport(req)
    else -> throw UnknownRequestClass(req.javaClass)
}

fun ProcessingContext.toTransport(req: VcReadRequest) {
    command = RcrsCommand.READ
    request = InnerReadReqImpl(
        system = InnerSystem(
            rqUid = req.system?.rqUid ?: "",
            rqTm = req.system?.rqTm ?: "",
        ),
        filtered = FilterInnerObj(
            dateTime = req.filtered?.dateTime ?: "",
            props = req.filtered?.props ?: mutableListOf(),
            city = req.filtered?.city ?: "",
            platform = req.filtered?.platform?.toTransportPlatform() ?: InnerPlatform.NONE,
        )
    )
    workMode = req.debug?.mode?.transportToWorkMode() ?: DebugMode.NONE
    stubCase = req.debug?.stub?.transportToWorkMode() ?: DebugStub.NONE
}

fun ProcessingContext.toTransport(req: VcStatRequest) {
    command = RcrsCommand.STAT
    workMode = req.debug?.mode?.transportToWorkMode() ?: DebugMode.NONE
    stubCase = req.debug?.stub?.transportToWorkMode() ?: DebugStub.NONE
    request = InnerStatReqImpl(
        system = InnerSystem(
            rqUid = req.system?.rqUid ?: "",
            rqTm = req.system?.rqTm ?: "",
        ),
        filtered = FilterInnerStatObj(
            dateTime = req.filtered?.dateTime ?: "",
            props = req.filtered?.props ?: mutableListOf(),
        )
    )
}

fun ProcessingContext.toTransport(req: VcCommonRequest) {
    command = if(req.mode?.prop?.name == "subscribe") RcrsCommand.SUBSCRIBE else RcrsCommand.CANCELED
    request = InnerCommonReqImpl(
        system = InnerSystem(
            rqUid = req.system?.rqUid ?: "",
            rqTm = req.system?.rqTm ?: "",
        ),
        mode = req.mode?.prop?.name ?: "NONE",
        sub = req.sub?.props?.let {
            InnerReqObject(
                email = req.sub?.email ?: "",
                props = it
            )
        }
    )
    workMode = req.debug?.mode?.transportToWorkMode() ?: DebugMode.NONE
    stubCase = req.debug?.stub?.transportToWorkMode() ?: DebugStub.NONE
}

fun RequestDebugMode.transportToWorkMode(): DebugMode = when(this) {
    RequestDebugMode.NONE -> DebugMode.NONE
    RequestDebugMode.PROD -> DebugMode.PROD
    RequestDebugMode.DEV -> DebugMode.DEV
    RequestDebugMode.UAT -> DebugMode.UAT
    RequestDebugMode.STUB -> DebugMode.STUB
}

fun RequestDebugStub.transportToWorkMode(): DebugStub = when(this) {
    RequestDebugStub.NONE -> DebugStub.NONE
    RequestDebugStub.SUCCESS -> DebugStub.SUCCESS
    RequestDebugStub.CANNOT_CANCELED -> DebugStub.CANNOT_CANCELED
    RequestDebugStub.CANNOT_READ -> DebugStub.CANNOT_READ
    RequestDebugStub.CANNOT_STAT -> DebugStub.CANNOT_STAT
    RequestDebugStub.CANNOT_COMPUTE -> DebugStub.CANNOT_COMPUTE
}

fun Platform.toTransportPlatform(): InnerPlatform = when(this) {
    Platform.HH -> InnerPlatform.HH
    Platform.HABR -> InnerPlatform.HABR
    Platform.SUPER_JOB -> InnerPlatform.SUPER_JOB
}