package com.otus.kfl.rcrs.domain

interface BaseQuery {
    var command: String
    var from: String
    var agrs: String
    var condition: String
}
