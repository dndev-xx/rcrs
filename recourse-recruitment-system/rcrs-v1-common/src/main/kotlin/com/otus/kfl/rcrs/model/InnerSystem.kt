package com.otus.kfl.rcrs.model

data class InnerSystem(
    var rqUid: String = "",
    var rqTm: String = ""
) {
    companion object {
        private val NONE = InnerSystem()
    }
}
