package org.tty.server.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.*
import org.springframework.web.bind.annotation.*
import org.tty.server.forms.UserForm
import org.tty.server.forms.UserLoginForm
import org.tty.server.model.uni.ApiResponse
import org.tty.server.service.UserService

/**
 * 用户控制器
 */
@RestController
class UserController {
    @Autowired
    private lateinit var userService: UserService

    /**
     * 注册一个新的用户
     */
    @RequestMapping("/register", method = [RequestMethod.GET, RequestMethod.POST])
    @ResponseBody
    fun register(@Validated user: UserForm): ApiResponse<Any> = userService.register(user)

    /**
     * 登录一个用户
     */
    @RequestMapping("/login", method= [RequestMethod.GET, RequestMethod.POST])
    @ResponseBody
    fun login(@Validated user: UserLoginForm): ApiResponse<Any> = userService.login(user)

}