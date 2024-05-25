package com.otus.kfl.rcrs.model.impl

import com.otus.kfl.rcrs.model.BaseInnerReq
import com.otus.kfl.rcrs.model.FilterInnerStatObj
import com.otus.kfl.rcrs.model.InnerSystem
import com.otus.kfl.rcrs.model.RcrsLock

data class InnerStatReqImpl(
    override var system: InnerSystem = InnerSystem(),
    override var lock: RcrsLock = RcrsLock.NONE,
    var filtered: FilterInnerStatObj = FilterInnerStatObj()
): BaseInnerReq
