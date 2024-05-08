package com.otus.kfl.rcrs.events

import com.otus.kfl.rcrs.dto.request.SystemInfo

abstract class Event {
    abstract val system: SystemInfo
    abstract val type: String
}
