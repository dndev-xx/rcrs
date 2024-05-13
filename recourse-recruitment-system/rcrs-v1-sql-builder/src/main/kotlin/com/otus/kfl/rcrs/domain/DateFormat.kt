package com.otus.kfl.rcrs.domain

enum class DateFormat(val format: String) {
    YYYY_MM_DD("yyyy-MM-dd"),
    YYYY_MM_DD_SLASH("yyyy/MM/dd"),
    DD_MM_YYYY("dd/MM/yyyy"),
    MM_DD_YYYY("MM/dd/yyyy"),
    YYYYMMDD("yyyyMMdd"),
    DD_MON_YYYY("dd-MMM-yyyy")
}