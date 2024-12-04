package com.mydocent.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.mydocent"])
class MyDocentApplication

fun main(args: Array<String>) {
	runApplication<MyDocentApplication>(*args)
}