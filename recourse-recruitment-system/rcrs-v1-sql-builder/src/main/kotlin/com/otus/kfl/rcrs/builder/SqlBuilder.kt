package com.otus.kfl.rcrs.builder

import com.otus.kfl.rcrs.builder.CriteriaContext.Companion.getInstanceConditionContext
import com.otus.kfl.rcrs.builder.JoinContext.Companion.getInstanceJoinContext
import com.otus.kfl.rcrs.domain.Action
import com.otus.kfl.rcrs.domain.JoinMode
import com.otus.kfl.rcrs.domain.SqlQuery
import java.lang.StringBuilder
import java.util.StringJoiner

class SqlBuilder {

    private var _command: String = ""
    private var _from: StringBuilder = StringBuilder()
    private var _agrs: StringJoiner = StringJoiner(", ")
    private var _condition: StringBuilder = StringBuilder()

    fun mode(block: () -> Action) {
        mode(block, emptyList())
    }

    fun mode(block: () -> Action, agrs: List<String>) {
        _command = block.invoke().name
        if (agrs.isNotEmpty()) {
            agrs.forEach {
                _agrs.add(it)
            }
        }
    }

    fun where(block: CriteriaContext.() -> Unit) {
        val ctx = getInstanceConditionContext().apply(block)
        _condition.append("where ${ctx.criteriaBuild}")
        ctx.criteriaBuild.clear()
    }

    fun into(table: String, agrs: List<String>) {
        if (agrs.isNotEmpty()) {
            agrs.forEach { _agrs.add(it) }
        }
        _from.append("into $table ($_agrs)")
    }

    fun set(agrs: Map<String, String>) {
        _from.append("set")
        if (agrs.isNotEmpty()) {
            agrs.forEach { (k, v) -> _agrs.add("$k = $v") }
        }
    }

    fun values(values: List<String>) {
        _condition.append("values (${values.joinToString()})")
    }

    fun from(block: () -> String) {
        _from.append("from ${block.invoke()}")
    }

    fun from(table: String, alias: String) {
        _from.append("from $table $alias")
    }

    infix fun String?.`as`(value: String): String {
        return "$this $value"
    }

    fun table(block: () -> String) {
        _from.append("${block.invoke()} ")
    }

    fun join(block: JoinContext.() -> Unit) {
        val ctx = getInstanceJoinContext().apply(block)
        _from.append(" ${ctx.joinBuild}")
        ctx.joinBuild.clear()
    }

    infix fun JoinMode?.join(block: JoinContext.() -> Unit) {
        val ctx = getInstanceJoinContext()
        ctx.mode { this ?: JoinMode.INNER }
        ctx.apply(block)
        _from.append(" ${ctx.joinBuild}")
        ctx.joinBuild.clear()
    }

    fun build() = SqlQuery(
        command = _command,
        from = _from.toString(),
        agrs = if (_agrs.length() != 0) _agrs.toString() else "*",
        condition = _condition.toString()
    )
}