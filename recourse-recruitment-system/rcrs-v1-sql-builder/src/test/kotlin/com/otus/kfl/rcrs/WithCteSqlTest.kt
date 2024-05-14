package com.otus.kfl.rcrs

import com.otus.kfl.rcrs.SelectQuerySqlTest.Companion.checkSQL
import com.otus.kfl.rcrs.domain.Action
import kotlin.test.Test

class WithCteSqlTest {

    @Test
    fun `with purchased_products as (select id from product where status = purchased) select id from product where id in (select id from purchased_products)`() {
        val expected =
            "with purchased_products as (SELECT id from product where status = purchased) SELECT id from product where id in (SELECT id from purchased_products)"
        val real = with {
            "purchased_products" `as` {
                mode({ Action.SELECT }, listOf("id"))
                from("product")
                where {
                    "status" eq "purchased"
                }
            }
            query {
                mode({ Action.SELECT }, listOf("id"))
                from("product")
                where {
                    "id" `in` {
                        subquery {
                            mode({ Action.SELECT }, listOf("id"))
                            from("purchased_products")
                        }
                    }
                }
            }
        }
        checkSQL(expected, real.toString())
    }

    @Test
    fun `with purchased_products as (select id from product where status = purchased), foo as (select all from foo where foo = bar) select id from product where id in (select id from purchased_products)`() {
        val expected =
            "with purchased_products as (SELECT id from product where status = purchased), foo as (SELECT * from foo where foo = bar) SELECT id from product where id in (SELECT id from purchased_products)"
        val real = with {
            "purchased_products" `as` {
                mode({ Action.SELECT }, listOf("id"))
                from("product")
                where {
                    "status" eq "purchased"
                }
            }
            "foo" `as` {
                mode { Action.SELECT }
                from("foo")
                where { "foo" eq "bar" }
            }
            query {
                mode({ Action.SELECT }, listOf("id"))
                from("product")
                where {
                    "id" `in` {
                        subquery {
                            mode({ Action.SELECT }, listOf("id"))
                            from("purchased_products")
                        }
                    }
                }
            }
        }
        checkSQL(expected, real.toString())
    }
}