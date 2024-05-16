package com.otus.kfl.rcrs.domain

data class SqlQuery(
    override var command: String = "",
    override var from: String = "",
    override var agrs: String = "",
    override var condition: String = "",
) : BaseQuery {
    override fun toString(): String {
        return when (command.lowercase()) {
            "insert" -> "$command $from $condition"
            "update" -> "$command $from $agrs $condition"
            "delete" -> "$command $from $condition"
            else -> "$command $agrs $from${if (condition.isNotEmpty()) " $condition" else ""}"
        }
    }
}
