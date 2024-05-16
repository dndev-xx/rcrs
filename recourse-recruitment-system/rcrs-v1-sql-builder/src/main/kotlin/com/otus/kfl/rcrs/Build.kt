package com.otus.kfl.rcrs

import com.otus.kfl.rcrs.builder.SqlBuilder
import com.otus.kfl.rcrs.builder.WithBuilder

fun query(block: SqlBuilder.() -> Unit) = SqlBuilder().apply(block).build()

fun with(block: WithBuilder.() -> Unit) = WithBuilder().apply(block)
