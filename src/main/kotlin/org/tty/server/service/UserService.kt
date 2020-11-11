package org.tty.server.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.tty.server.converter.UserTool
import org.tty.server.exception.HttpStatusException
import org.tty.server.forms.UserForm
import org.tty.server.forms.UserLoginForm
import org.tty.server.model.entity.UserToken
import org.tty.server.model.uni.ApiResponse
import org.tty.server.model.uni.ResponseToken
import org.tty.server.model.uni.ResponseTokens
import org.tty.server.repository.UserRepository
import org.tty.server.repository.UserTokenRepository
import org.tty.server.security.UserEncryptor

@Component
class UserService {

    @Autowired
    private lateinit var userRepository: UserRepository
    @Autowired
    private lateinit var userTokenRepository: UserTokenRepository
    @Autowired
    private lateinit var userEncryptor: UserEncryptor

    fun register(userForm: UserForm) : ApiResponse<Any> {
        val user = UserTool.toUserNormal(userForm).apply { userEncryptor.encrypt(this) }
        // check whether is registered
        if (!userRepository.findById(user.uid).isEmpty) {
            return ApiResponse(ResponseTokens.Login.registered)
        }

        userRepository.save(user)
        return ApiResponse(ResponseTokens.ok, "已注册成功", user)
    }

    fun login(userLoginForm: UserLoginForm): ApiResponse<Any> {
        val formUser = UserTool.toUserNormal(userLoginForm).apply { userEncryptor.encrypt(this) } //加密表单
        val user = userRepository.findById(userLoginForm.uid)
        when {
            user.isEmpty -> {
                throw HttpStatusException(ApiResponse(ResponseTokens.Login.noUser))
            }
            user.get().password == formUser.password -> {
                throw HttpStatusException(ApiResponse(ResponseTokens.Login.passwordError))
            }
        }

        // 执行业务逻辑
        val token = UserTool.generateToken(user.get())
        userTokenRepository.save(token) // 保存token
        return ApiResponse(ResponseTokens.ok, "登录成功", token)
    }

}