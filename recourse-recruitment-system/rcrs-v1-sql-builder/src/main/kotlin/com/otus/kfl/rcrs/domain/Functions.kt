package com.otus.kfl.rcrs.domain

fun toDate(date: String, format: DateFormat): String {
    return "TO_DATE('$date', '${format.format}')"
}