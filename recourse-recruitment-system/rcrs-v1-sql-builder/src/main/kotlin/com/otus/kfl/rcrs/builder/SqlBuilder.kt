package com.otus.kfl.rcrs.builder

import com.otus.kfl.rcrs.builder.CriteriaContext.Companion.getInstanceConditionContext
import com.otus.kfl.rcrs.domain.Action
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

    fun from(table: String) {
        _from.append("from $table")
    }

    fun table(table: String) {
        _from.append("$table ")
    }

    fun build() = SqlQuery(
        command = _command,
        from = _from.toString(),
        agrs = if (_agrs.length() != 0) _agrs.toString() else "*",
        condition = _condition.toString()
    )
}