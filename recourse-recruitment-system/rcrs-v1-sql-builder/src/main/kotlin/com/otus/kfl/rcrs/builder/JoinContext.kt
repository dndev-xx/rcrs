package com.otus.kfl.rcrs.builder

import com.otus.kfl.rcrs.builder.CriteriaContext.Companion.getInstanceConditionContext
import com.otus.kfl.rcrs.domain.JoinMode
import com.otus.kfl.rcrs.dsl.SqlCustomMarker

@SqlCustomMarker
class JoinContext private constructor() {

    var joinBuild: StringBuilder = StringBuilder()

    companion object {
        @Volatile
        private var instance: JoinContext? = null

        fun getInstanceJoinContext(): JoinContext {
            return instance ?: synchronized(this) {
                instance ?: JoinContext().also { instance = it }
            }
        }
    }

    fun mode(block: () -> JoinMode) {
        joinBuild.append("${block.invoke().name} join ")
    }

    fun table(block: () -> String) {
        joinBuild.append("${block.invoke()} ")
    }

    fun on(block: CriteriaContext.() -> Unit) {
        val ctx = getInstanceConditionContext().apply(block)
        joinBuild.append("on ${ctx.criteriaBuild}")
        ctx.criteriaBuild.clear()
    }
}