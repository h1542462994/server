package org.tty.server.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.tty.server.converter.UserConverter
import org.tty.server.forms.UserForm
import org.tty.server.model.ApiResponse
import org.tty.server.model.ResponseToken
import org.tty.server.model.ResponseTokens
import org.tty.server.repository.UserRepository
import org.tty.server.security.UserEncryptor

@Component
class UserService {

    @Autowired
    private lateinit var userRepository: UserRepository

    fun register(userForm: UserForm) : ApiResponse<Any> {
        val user = UserConverter().toUserNormal(userForm).apply { UserEncryptor().encrypt(this) }
        // check whether is registered
        if (!userRepository.findById(user.uid).isEmpty) {
            return ApiResponse(ResponseTokens.Login.registered)
        }

        userRepository.save(user)
        return ApiResponse(ResponseTokens.ok, "已注册成功", user)
    }

}