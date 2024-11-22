package com.mydocent.auth.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {
    @GetMapping("/auth-users")
    fun requiredAuth() = "인증된 요청만 받을 수 있어!"

    @GetMapping("/free-users")
    fun freeAuth() = "인증하지 않아도 받을 수 있어!"
}