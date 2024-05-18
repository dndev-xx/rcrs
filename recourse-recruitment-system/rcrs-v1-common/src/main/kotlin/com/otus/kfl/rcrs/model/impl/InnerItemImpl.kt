package com.otus.kfl.rcrs.model.impl

import com.otus.kfl.rcrs.model.BaseInnerItem
import com.otus.kfl.rcrs.model.BaseInnerModelObj

data class InnerItemImpl(
    override var id: String = "",
    override var ownerId: String = "",
    override var lock: String = "",
    var model: BaseInnerModelObj = BaseInnerModelObj()
): BaseInnerItem
