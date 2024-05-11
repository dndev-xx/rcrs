package com.otus.kfl.rcrs

import com.otus.kfl.rcrs.SelectQuerySqlTest.Companion.checkSQL
import com.otus.kfl.rcrs.domain.Action
import kotlin.test.Test

class InsertQuerySqlTest {

    @Test
    fun `when insert into table(id) values (1)`() {
        val expected = "INSERT into table (id) values (1)"
        val real = query {
            mode { Action.INSERT }
            into("table", listOf("id"))
            values(listOf("1"))
        }
        checkSQL(expected, real.toString())
    }

    @Test
    fun `when insert into table(id, name, age) values (1, bob, 23)`() {
        val expected = "INSERT into table (id, name, age) values (1, bob, 23)"
        val real = query {
            mode { Action.INSERT }
            into("table", listOf("id", "name", "age"))
            values(listOf("1", "bob", "23"))
        }
        checkSQL(expected, real.toString())
    }
}