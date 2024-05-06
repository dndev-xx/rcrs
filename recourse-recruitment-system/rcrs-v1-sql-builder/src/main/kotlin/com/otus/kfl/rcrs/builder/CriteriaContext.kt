package com.otus.kfl.rcrs.builder

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

    infix fun String?.eq(value: String?) {
        criteriaBuild.append("$this = $value")
    }

    infix fun String?.notEq(value: String?) {
        criteriaBuild.append("$this <> $value")
    }
}
