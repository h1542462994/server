package org.tty.server.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.*
import org.springframework.web.bind.annotation.*
import org.tty.server.forms.UserForm
import org.tty.server.model.ApiResponse
import org.tty.server.service.UserService
import javax.validation.Valid

@RestController
class UserController {
    @Autowired
    private lateinit var userService: UserService

    @RequestMapping("/register", method = [RequestMethod.GET, RequestMethod.POST])
    @ResponseBody
    fun register(@Validated user: UserForm): ApiResponse<Any> = userService.register(user)



}