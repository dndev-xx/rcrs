package com.otus.kfl.rcrs.model

interface BaseInnerResp {
    var responseType: String
    var result: InnerResult
    var errors: MutableList<RcrsError>
}