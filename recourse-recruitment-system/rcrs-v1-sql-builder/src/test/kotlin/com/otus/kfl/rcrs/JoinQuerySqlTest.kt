package com.otus.kfl.rcrs

import com.otus.kfl.rcrs.SelectQuerySqlTest.Companion.checkSQL
import com.otus.kfl.rcrs.domain.Action
import com.otus.kfl.rcrs.domain.JoinMode
import kotlin.test.Test

class JoinQuerySqlTest {

    @Test
    fun `when select pname dnumber from person p inner join document d on pdoc_id = did`() {
        val expected = "SELECT p.name, d.number from person p INNER join document d on p.doc_id = d.id "
        val real = query {
            mode({
                Action.SELECT
            }, listOf("p.name", "d.number"))
            from("person" alias "p")
            join {
                mode(JoinMode.INNER)
                table("document" alias "d")
                on {
                    "p.doc_id" eq "d.id"
                }
            }
        }
        checkSQL(expected, real.toString())
    }

    @Test
    fun `when select pname from person p inner join document d on pdoc_id = did where pname = martin`() {
        val expected = "SELECT p.name from person p INNER join document d on p.doc_id = d.id where p.name = martin"
        val real = query {
            mode({
                Action.SELECT
            }, listOf("p.name"))
            from("person" alias "p")
            join {
                mode(JoinMode.INNER)
                table("document" alias "d")
                on {
                    "p.doc_id" eq "d.id"
                }
            }
            where {
                "p.name" eq "martin"
            }
        }
        checkSQL(expected, real.toString())
    }

    @Test
    fun `when select pname from person p inner join document d on pdoc_id = did and panother_id = danother_id`() {
        val expected =
            "SELECT p.name from person p INNER join document d on p.doc_id = d.id and p.another_id = d.another_id "
        val real = query {
            mode({
                Action.SELECT
            }, listOf("p.name"))
            from("person" alias "p")
            join {
                mode(JoinMode.INNER)
                table("document" alias "d")
                on {
                    "p.doc_id" eq "d.id"
                    and {
                        "p.another_id" eq "d.another_id"
                    }
                }
            }
        }
        checkSQL(expected, real.toString())
    }

    @Test
    fun `when select pname from person p inner join document d on pdoc_id = did and panother_id = danother_id where pname = martin`() {
        val expected =
            "SELECT p.name from person p INNER join document d on p.doc_id = d.id and p.another_id = d.another_id where p.name = martin"
        val real = query {
            mode({
                Action.SELECT
            }, listOf("p.name"))
            from("person" alias "p")
            join {
                mode(JoinMode.INNER)
                table("document" alias "d")
                on {
                    "p.doc_id" eq "d.id"
                    and {
                        "p.another_id" eq "d.another_id"
                    }
                }
            }
            where {
                "p.name" eq "martin"
            }
        }
        checkSQL(expected, real.toString())
    }

    @Test
    fun `when select all from orders o join customers c on ocustomer_id = cid INNER join products p on oproduct_id = pid where pname = iphone`() {
        val expected =
            "SELECT * from orders o INNER join customers c on o.customer_id = c.id INNER join products p on o.product_id = p.id where p.name = iphone"
        val real = query {
            mode { Action.SELECT }
            from("orders" alias "o")
            join {
                mode(JoinMode.INNER)
                table("customers" alias "c")
                on {
                    "o.customer_id" eq "c.id"
                }
            }
            join {
                mode(JoinMode.INNER)
                table("products" alias "p")
                on {
                    "o.product_id" eq "p.id"
                }
            }
            where {
                "p.name" eq "iphone"
            }
        }
        checkSQL(expected, real.toString())
    }
}