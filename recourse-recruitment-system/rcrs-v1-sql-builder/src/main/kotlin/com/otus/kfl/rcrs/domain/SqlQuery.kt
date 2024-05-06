package com.otus.kfl.rcrs.domain

data class SqlQuery(
    override var command: String = "",
    override var from: String = "",
    override var agrs: String = "",
    override var condition: String = "",
) : BaseQuery {
    override fun toString(): String {
        return if(condition.isEmpty()) {
            "$command $agrs $from"
        } else {
            "$command $agrs $from $condition"
        }
    }
}
