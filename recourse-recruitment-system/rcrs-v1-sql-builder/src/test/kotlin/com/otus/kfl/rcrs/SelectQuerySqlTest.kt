package com.otus.kfl.rcrs

import com.otus.kfl.rcrs.domain.Action
import kotlin.test.Test
import kotlin.test.assertEquals

class SelectQuerySqlTest {

    companion object {
        fun checkSQL(expected: String, sql: String) {
            assertEquals(expected, sql)
        }
    }

    @Test
    fun `simple select all from table`() {
        val expected = "SELECT * from table "

        val real = query {
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

        val real = query {
            mode({
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

        val real = query {
            mode({
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

        val real = query {
            mode({
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
    fun `when select all from products where product_id in (2, 5, 8)`() {
        val expected = "SELECT * from products where product_id in (2, 5, 8)"
        val real = query {
            mode { Action.SELECT }
            from("products")
            where {
                "product_id" `in` listOf("2", "5", "8")
            }
        }
        checkSQL(expected, real.toString())
    }

    @Test
    fun `when select all from products where category_id = 1 and product_id in (2, 5, 8) and name = iphone`() {
        val expected = "SELECT * from products where category_id = 1 and product_id in (2, 5, 8) and name = iphone"
        val real = query {
            mode { Action.SELECT }
            from("products")
            where {
                "category_id" eq "1"
                and {
                    "product_id" `in` listOf("2", "5", "8")
                }
                and {
                    "name" eq "iphone"
                }
            }
        }
        checkSQL(expected, real.toString())
    }

    @Test
    fun `when select all from products where product_id not in (2, 5, 8)`() {
        val expected = "SELECT * from products where product_id not in (2, 5, 8)"
        val real = query {
            mode { Action.SELECT }
            from("products")
            where {
                "product_id" notIn listOf("2", "5", "8")
            }
        }
        checkSQL(expected, real.toString())
    }
}