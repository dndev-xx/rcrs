package com.otus.kfl.rcrs.model.impl

import com.otus.kfl.rcrs.model.BaseInnerReq
import com.otus.kfl.rcrs.model.InnerReqObject
import com.otus.kfl.rcrs.model.InnerSystem
import com.otus.kfl.rcrs.model.RcrsLock

data class InnerCommonReqImpl(
    override var system: InnerSystem = InnerSystem(),
    override var lock: RcrsLock = RcrsLock.NONE,
    var mode: String = "NONE",
    var sub: InnerReqObject? = null,
): BaseInnerReq
