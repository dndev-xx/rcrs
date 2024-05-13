package com.otus.kfl.rcrs.builder

import com.otus.kfl.rcrs.builder.SubqueryContext.Companion.getInstanceSubqueryContext
import com.otus.kfl.rcrs.dsl.SqlCustomMarker

@SqlCustomMarker
class CriteriaContext private constructor() {
    var criteriaBuild: StringBuilder = StringBuilder()

    companion object {
        @Volatile
        private var instance: CriteriaContext? = null

        fun getInstanceConditionContext(): CriteriaContext {
            return instance ?: synchronized(this) {
                instance ?: CriteriaContext().also { instance = it }
            }
        }
    }

    fun and(block: CriteriaContext.() -> Unit) {
        criteriaBuild.append(" and ")
        this.apply(block)
    }

    fun or(block: CriteriaContext.() -> Unit) {
        criteriaBuild.append(" or ")
        this.apply(block)
    }

    infix fun String?.`in`(agrs: List<String>) {
        criteriaBuild.append("$this in (${agrs.joinToString(", ")})")
    }

    infix fun String?.`in`(block: SubqueryContext.() -> Unit) {
        val temp = criteriaBuild.toString()
        val ctx = getInstanceSubqueryContext()
        ctx.context.append("$this in ")
        ctx.apply(block)
        criteriaBuild.append("$temp${ctx.context}")
        ctx.context.clear()
    }

    infix fun String?.notIn(agrs: List<String>) {
        this `in` agrs
        this?.let { insertNotAfter(this) }
    }

    infix fun String?.notIn(block: SubqueryContext.() -> Unit) {
        this `in` block
        this?.let { insertNotAfter(this) }
    }

    private fun insertNotAfter(comparable: String) {
        criteriaBuild.insert(comparable.length, " not")
    }

    infix fun String?.eq(value: String?) {
        criteriaBuild.append("$this = $value")
    }

    infix fun String?.notEq(value: String?) {
        criteriaBuild.append("$this <> $value")
    }
}
