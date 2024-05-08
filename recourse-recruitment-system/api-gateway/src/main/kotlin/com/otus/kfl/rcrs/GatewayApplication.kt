package com.otus.kfl.rcrs

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GatewayApplication {

    companion object {
        val log: Logger = LoggerFactory.getLogger(GatewayApplication::class.java)
    }
}

fun main(args: Array<String>) {
    runApplication<GatewayApplication>(*args)
}