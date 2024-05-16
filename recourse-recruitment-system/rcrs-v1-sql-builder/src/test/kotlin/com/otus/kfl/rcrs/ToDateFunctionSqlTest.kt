package com.otus.kfl.rcrs

import com.otus.kfl.rcrs.SelectQuerySqlTest.Companion.checkSQL
import com.otus.kfl.rcrs.domain.Action
import com.otus.kfl.rcrs.domain.DateFormat
import com.otus.kfl.rcrs.domain.toDate
import kotlin.test.Test

class ToDateFunctionSqlTest {

    @Test
    fun `select to date(2019 07 22, yyyy MM dd) from dual - using slash`() {
        val expected = "SELECT TO_DATE('2019/07/22', 'yyyy/MM/dd') from dual"
        val real = query {
            mode({ Action.SELECT }, listOf(toDate("2019/07/22", DateFormat.YYYY_MM_DD_SLASH)))
            from { "dual" }
        }
        checkSQL(expected, real.toString())
    }

    @Test
    fun `select to date(20190722, yyyyMMdd) from dual - conclusively`() {
        val expected = "SELECT TO_DATE('20190722', 'yyyyMMdd') from dual"
        val real = query {
            mode({ Action.SELECT }, listOf(toDate("20190722", DateFormat.YYYYMMDD)))
            from { "dual" }
        }
        checkSQL(expected, real.toString())
    }
}