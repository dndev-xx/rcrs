package com.otus.kfl.rcrs

import com.otus.kfl.rcrs.domain.Action
import kotlin.test.Test
import kotlin.test.assertEquals

class BuildSqlTest {

    @Test
    fun `simple select all from table`() {
        val expected = "SELECT * from table"

        val real = buildSql {
            mode {
                Action.SELECT
            }
            from("table")
        }
        checkSQL(expected, real.toString())
    }

    @Test
    fun `when select agrs from table where id = 1`() {
        val expected = "SELECT id,name from table where id = 1"

        val real = buildSql {
            mode ({
                Action.SELECT
            }, listOf("id", "name"))
            from("table")
            where {
                "id" eq "1"
            }
        }

        checkSQL(expected, real.toString())
    }

    @Test
    fun `when select agrs from table where id = 1 and name = 'test'`() {
        val expected = "SELECT id,name from table where id = 1 and name = 'test'"

        val real = buildSql {
            mode ({
                Action.SELECT
            }, listOf("id", "name"))
            from("table")
            where {
                "id" eq "1"
                and {
                    "name" eq "'test'"
                }
            }
        }
        checkSQL(expected, real.toString())
    }

    @Test
    fun `when select agrs from table where id = 1 and name = 'test' or name = 'testOr'`() {
        val expected = "SELECT id,name from table where id = 1 and name = 'test' or name = testOr"

        val real = buildSql {
            mode ({
                Action.SELECT
            }, listOf("id", "name"))
            from("table")
            where {
                "id" eq "1"
                and {
                    "name" eq "'test'"
                }
                or {
                    "name" eq "testOr"
                }
            }
        }
        checkSQL(expected, real.toString())
    }

    private fun checkSQL(expected: String, sql: String) {
        assertEquals(expected, sql)
    }
}