package com.otus.kfl.rcrs

import com.otus.kfl.rcrs.builder.SqlBuilder

fun query(block: SqlBuilder.() -> Unit) = SqlBuilder().apply(block).build()
