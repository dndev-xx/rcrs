package com.otus.kfl.rcrs

import com.otus.kfl.rcrs.builder.SqlBuilder

fun buildSql(block: SqlBuilder.() -> Unit) = SqlBuilder().apply(block).build()
