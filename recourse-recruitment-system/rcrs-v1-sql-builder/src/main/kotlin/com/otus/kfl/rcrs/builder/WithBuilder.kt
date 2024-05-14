package com.otus.kfl.rcrs.builder

import com.otus.kfl.rcrs.dsl.SqlCustomMarker

@SqlCustomMarker
class WithBuilder {

    private var context: StringBuilder = StringBuilder()

    private var counter: Int = 0

    override fun toString(): String {
        return "with $context"
    }

    infix fun String?.`as`(block: SqlBuilder.() -> Unit) {
        val cte = SqlBuilder().apply(block).build()
        if (counter > 0) {
            context.append(", ")
        }
        context.append("$this as ($cte)")
        counter++
    }

    fun query(block: SqlBuilder.() -> Unit) {
        val query = SqlBuilder().apply(block).build()
        context.append(" $query")
    }
}