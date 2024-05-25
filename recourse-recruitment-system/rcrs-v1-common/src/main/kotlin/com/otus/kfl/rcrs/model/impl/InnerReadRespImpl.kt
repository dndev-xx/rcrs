package com.otus.kfl.rcrs.model.impl

import com.otus.kfl.rcrs.model.BaseInnerModelObj
import com.otus.kfl.rcrs.model.BaseInnerResp
import com.otus.kfl.rcrs.model.InnerResult
import com.otus.kfl.rcrs.model.RcrsError

data class InnerReadRespImpl(
    override var responseType: String = "",
    override var result: InnerResult = InnerResult.NONE,
    override var errors: MutableList<RcrsError> = mutableListOf(),
    var vacancies: MutableList<BaseInnerModelObj> = mutableListOf()
): BaseInnerResp


