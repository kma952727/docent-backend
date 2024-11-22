package com.mydocent.auth.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class UserController {
    @GetMapping("/auth-users")
    fun requiredAuth(): String {
        println("auth-users")
        return "인증된 요청만 받을 수 있어!"
    }

    @GetMapping("/free-users")
    fun freeAuth(): String {
        println("free-users 접근!")
        return "인증하지 않아도 받을 수 있어!"
    }
}