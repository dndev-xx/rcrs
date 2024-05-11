package com.otus.kfl.rcrs

import com.otus.kfl.rcrs.SelectQuerySqlTest.Companion.checkSQL
import com.otus.kfl.rcrs.domain.Action
import kotlin.test.Test

class DeleteQuerySqlTest {

    @Test
    fun `when delete from table where id = 1`() {
        val expected = "DELETE from table where id = 1"
        val real = query {
            mode { Action.DELETE }
            from("table")
            where {
                "id" eq "1"
            }
        }
        checkSQL(expected, real.toString())
    }
}