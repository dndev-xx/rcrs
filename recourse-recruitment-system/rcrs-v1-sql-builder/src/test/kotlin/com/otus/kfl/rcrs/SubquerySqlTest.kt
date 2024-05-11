package com.otus.kfl.rcrs

import com.otus.kfl.rcrs.SelectQuerySqlTest.Companion.checkSQL
import com.otus.kfl.rcrs.domain.Action
import kotlin.test.Test

class SubquerySqlTest {

    @Test
    fun `when select all from product where category_id in (select id from category where name = electronic)`() {
        val expected = "SELECT * from product where category_id in (SELECT id from category where name = electronic)"
        val real = query {
            mode { Action.SELECT }
            from("product")
            where {
                `in`("category_id") {
                    subquery {
                        mode({ Action.SELECT }, listOf("id"))
                        from("category")
                        where {
                            "name" eq "electronic"
                        }
                    }
                }
            }
        }
        checkSQL(expected, real.toString())
    }

    @Test
    fun `when select all from product where category_id in (select id from category where name = electronic) and foo_id in (select id from foo where foo = foo)`() {
        val expected = "SELECT * from product where category_id in (SELECT id from category where name = electronic) and foo_id in (SELECT id from foo where foo = foo)"
        val real = query {
            mode { Action.SELECT }
            from("product")
            where {
                `in`("category_id") {
                    subquery {
                        mode({ Action.SELECT }, listOf("id"))
                        from("category")
                        where {
                            "name" eq "electronic"
                        }
                    }
                }
                and {
                    `in`("foo_id") {
                        subquery {
                            mode({ Action.SELECT }, listOf("id"))
                            from("foo")
                            where {
                                "foo" eq "foo"
                            }
                        }
                    }
                }
            }
        }
        checkSQL(expected, real.toString())
    }

    @Test
    fun `when select all from product where name = iphone and category_id in (select id from category where name = electronic)`() {
        val expected = "SELECT * from product where name = iphone and category_id in (SELECT id from category where name = electronic)"
        val real = query {
            mode { Action.SELECT }
            from("product")
            where {
                "name" eq "iphone"
                and {
                    `in`("category_id") {
                        subquery {
                            mode({ Action.SELECT }, listOf("id"))
                            from("category")
                            where {
                                "name" eq "electronic"
                            }
                        }
                    }
                }
            }
        }
        checkSQL(expected, real.toString())
    }

    @Test
    fun `when select all from product where category_id in (select id from category where name = electronic) and name = iphone`() {
        val expected = "SELECT * from product where category_id in (SELECT id from category where name = electronic) and name = iphone"
        val real = query {
            mode { Action.SELECT }
            from("product")
            where {
                `in`("category_id") {
                    subquery {
                        mode({ Action.SELECT }, listOf("id"))
                        from("category")
                        where {
                            "name" eq "electronic"
                        }
                    }
                }
                and {
                    "name" eq "iphone"
                }
            }
        }
        checkSQL(expected, real.toString())
    }

    @Test
    fun `when select all from product where name = iphone and foo = foo and category_id in (select id from category where name = electronic)`() {
        val expected = "SELECT * from product where name = iphone and foo = foo and category_id in (SELECT id from category where name = electronic)"
        val real = query {
            mode { Action.SELECT }
            from("product")
            where {
                "name" eq "iphone"
                and {
                    "foo" eq "foo"
                }
                and {
                    `in`("category_id") {
                        subquery {
                            mode({ Action.SELECT }, listOf("id"))
                            from("category")
                            where {
                                "name" eq "electronic"
                            }
                        }
                    }
                }
            }
        }
        checkSQL(expected, real.toString())
    }

    @Test
    fun `when select all from product where name = iphone and foo = foo and baz = baz and category_id in (select id from category where name = electronic)`() {
        val expected = "SELECT * from product where name = iphone and foo = foo and baz = baz and category_id in (SELECT id from category where name = electronic)"
        val real = query {
            mode { Action.SELECT }
            from("product")
            where {
                "name" eq "iphone"
                and {
                    "foo" eq "foo"
                }
                and {
                    "baz" eq "baz"
                }
                and {
                    `in`("category_id") {
                        subquery {
                            mode({ Action.SELECT }, listOf("id"))
                            from("category")
                            where {
                                "name" eq "electronic"
                            }
                        }
                    }
                }
            }
        }
        checkSQL(expected, real.toString())
    }

    @Test
    fun `when select all from product where foo = foo and baz = baz and category_id in (select id from category where name = electronic) and name = iphone`() {
        val expected = "SELECT * from product where foo = foo and baz = baz and category_id in (SELECT id from category where name = electronic) and name = iphone"
        val real = query {
            mode { Action.SELECT }
            from("product")
            where {
                "foo" eq "foo"
                and {
                    "baz" eq "baz"
                }
                and {
                    `in`("category_id") {
                        subquery {
                            mode({ Action.SELECT }, listOf("id"))
                            from("category")
                            where {
                                "name" eq "electronic"
                            }
                        }
                    }
                }
                and {
                    "name" eq "iphone"
                }
            }
        }
        checkSQL(expected, real.toString())
    }
}