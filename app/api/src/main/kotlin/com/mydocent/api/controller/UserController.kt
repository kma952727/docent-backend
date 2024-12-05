package com.mydocent.api.controller

import com.mydocent.user.service.UserManageService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@Tag(name = "UserController - 사용자에 관한 기능")
@RestController
@RequestMapping("/api")
class UserController(private val userManageService: UserManageService)