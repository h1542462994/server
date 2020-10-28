package org.tty.server.controller

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import org.tty.server.forms.UserForm

@RestController
class UserController {
    @GetMapping("/login")
    @ResponseBody
    fun login(@Validated user: UserForm): String{
        return "hello"
    }
}