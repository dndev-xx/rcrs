package com.otus.kfl.rcrs

import com.otus.kfl.rcrs.SelectQuerySqlTest.Companion.checkSQL
import com.otus.kfl.rcrs.domain.Action
import kotlin.test.Test

class UpdateQuerySqlTest {

    @Test
    fun `when update table set name = martin where id = 1`() {
        val expected = "UPDATE table set name = martin where id = 1"
        val real = query {
            mode { Action.UPDATE }
            table("table")
            set(mapOf("name" to "martin"))
            where {
                "id" eq "1"
            }
        }
        checkSQL(expected, real.toString())
    }

    @Test
    fun `when update table set name = martin, age = 23, position = expert where id = 1`() {
        val expected = "UPDATE table set name = martin, age = 23, position = expert where id = 1"
        val real = query {
            mode { Action.UPDATE }
            table("table")
            set(
                mapOf(
                    "name" to "martin",
                    "age" to "23",
                    "position" to "expert"
                )
            )
            where { "id" eq "1" }
        }
        checkSQL(expected, real.toString())
    }
}