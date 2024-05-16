package com.otus.kfl.rcrs.builder

import com.otus.kfl.rcrs.builder.CriteriaContext.Companion.getInstanceConditionContext
import com.otus.kfl.rcrs.dsl.SqlCustomMarker

@SqlCustomMarker
class SubqueryContext private constructor() {

    var context: StringBuilder = StringBuilder()

    companion object {
        @Volatile
        private var instance: SubqueryContext? = null

        fun getInstanceSubqueryContext(): SubqueryContext {
            return instance ?: synchronized(this) {
                instance ?: SubqueryContext().also { instance = it }
            }
        }
    }

    fun subquery(block: SqlBuilder.() -> Unit) {
        val ctx = getInstanceConditionContext()
        ctx.criteriaBuild.clear()
        val subquery = SqlBuilder().apply(block).build()
        context.append("($subquery)")
    }
}