package com.otus.kfl.rcrs.model

import kotlin.jvm.JvmInline

@JvmInline
value class RcrsLock(private val lock: String) {
    fun asString() = lock

    companion object {
        val NONE = RcrsLock("")
    }
}