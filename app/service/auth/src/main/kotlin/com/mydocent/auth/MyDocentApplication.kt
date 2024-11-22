package com.mydocent.auth

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication

@EntityScan(basePackages = ["com.mydocent"])
@SpringBootApplication
class MyDocentApplication

fun main(args: Array<String>) {
	runApplication<MyDocentApplication>(*args)
}