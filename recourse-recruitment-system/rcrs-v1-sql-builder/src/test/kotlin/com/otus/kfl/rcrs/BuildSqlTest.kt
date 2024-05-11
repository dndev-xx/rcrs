package com.otus.kfl.rcrs

import com.otus.kfl.rcrs.domain.Action
import kotlin.test.Test
import kotlin.test.assertEquals

class BuildSqlTest {

    @Test
    fun `simple select all from table`() {
        val expected = "SELECT * from table "

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
        val expected = "SELECT id, name from table where id = 1"

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
        val expected = "SELECT id, name from table where id = 1 and name = 'test'"

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
        val expected = "SELECT id, name from table where id = 1 and name = 'test' or name = testOr"

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

    @Test
    fun `when insert into table(id) values (1)`() {
        val expected = "INSERT into table (id) values (1)"
        val real = buildSql {
            mode { Action.INSERT }
            into("table", listOf("id"))
            values(listOf("1"))
        }
        checkSQL(expected, real.toString())
    }

    @Test
    fun `when insert into table(id, name, age) values (1, bob, 23)`() {
        val expected = "INSERT into table (id, name, age) values (1, bob, 23)"
        val real = buildSql {
            mode { Action.INSERT }
            into("table", listOf("id", "name", "age"))
            values(listOf("1", "bob", "23"))
        }
        checkSQL(expected, real.toString())
    }

    @Test
    fun `when update table set name = martin where id = 1`() {
        val expected = "UPDATE table set name = martin where id = 1"
        val real = buildSql {
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
        val real = buildSql {
            mode { Action.UPDATE }
            table("table")
            set(mapOf(
                "name" to "martin",
                "age" to "23",
                "position" to "expert"
            ))
            where { "id" eq "1" }
        }
        checkSQL(expected, real.toString())
    }

    @Test
    fun `when delete from table where id = 1`() {
        val expected = "DELETE from table where id = 1"
        val real = buildSql {
            mode { Action.DELETE }
            from("table")
            where {
                "id" eq "1"
            }
        }
        checkSQL(expected, real.toString())
    }

    private fun checkSQL(expected: String, sql: String) {
        assertEquals(expected, sql)
    }
}