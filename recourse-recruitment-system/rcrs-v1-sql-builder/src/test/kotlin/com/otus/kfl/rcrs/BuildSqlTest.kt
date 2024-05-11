package com.otus.kfl.rcrs

import com.otus.kfl.rcrs.domain.Action
import com.otus.kfl.rcrs.domain.JoinMode
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

        val real = buildSql {
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

        val real = buildSql {
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

    @Test
    fun `when select pname dnumber from person p inner join document d on pdoc_id = did`() {
        val expected = "SELECT p.name, d.number from person p INNER join document d on p.doc_id = d.id "
        val real = buildSql {
            mode({
                Action.SELECT
            }, listOf("p.name", "d.number"))
            from("person", "p")
            join {
                mode(JoinMode.INNER)
                table("document", "d")
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
        val real = buildSql {
            mode({
                Action.SELECT
            }, listOf("p.name"))
            from("person", "p")
            join {
                mode(JoinMode.INNER)
                table("document", "d")
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
        val expected = "SELECT p.name from person p INNER join document d on p.doc_id = d.id and p.another_id = d.another_id "
        val real = buildSql {
            mode({
                Action.SELECT
            }, listOf("p.name"))
            from("person", "p")
            join {
                mode(JoinMode.INNER)
                table("document", "d")
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
        val expected = "SELECT p.name from person p INNER join document d on p.doc_id = d.id and p.another_id = d.another_id where p.name = martin"
        val real = buildSql {
            mode({
                Action.SELECT
            }, listOf("p.name"))
            from("person", "p")
            join {
                mode(JoinMode.INNER)
                table("document", "d")
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
        val expected = "SELECT * from orders o INNER join customers c on o.customer_id = c.id INNER join products p on o.product_id = p.id where p.name = iphone"
        val real = buildSql {
            mode { Action.SELECT }
            from("orders", "o")
            join {
                mode(JoinMode.INNER)
                table("customers", "c")
                on {
                    "o.customer_id" eq "c.id"
                }
            }
            join {
                mode(JoinMode.INNER)
                table("products", "p")
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

    private fun checkSQL(expected: String, sql: String) {
        assertEquals(expected, sql)
    }
}