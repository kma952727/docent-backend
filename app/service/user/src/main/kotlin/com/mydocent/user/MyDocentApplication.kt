package com.mydocent.user

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication

@EntityScan(basePackages = ["com.mydocent"])
@SpringBootApplication(scanBasePackages = ["com.mydocent"])
class MyDocentApplication

fun main(args: Array<String>) {
	runApplication<MyDocentApplication>(*args)
}