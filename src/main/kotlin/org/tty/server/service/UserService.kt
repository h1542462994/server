package org.tty.server.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.tty.server.common.UserTool
import org.tty.server.exception.HttpStatusException
import org.tty.server.forms.UserAutoLoginForm
import org.tty.server.forms.UserForm
import org.tty.server.forms.UserLoginForm
import org.tty.server.model.common.ApiResponse
import org.tty.server.model.common.ResponseTokens
import org.tty.server.model.common.UserCredit
import org.tty.server.repository.UserRepository
import org.tty.server.repository.UserTokenRepository
import org.tty.server.security.UserEncryptor
import java.sql.Timestamp
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*

@Component
class UserService {

    @Autowired
    private lateinit var userRepository: UserRepository
    @Autowired
    private lateinit var userTokenRepository: UserTokenRepository
    @Autowired
    private lateinit var userEncryptor: UserEncryptor

    /**
     * 注册
     */
    fun register(userForm: UserForm) : ApiResponse<Any> {
        val user = UserTool.toUserNormal(userForm).apply { userEncryptor.encrypt(this) }
        // check whether is registered
        if (!userRepository.findById(user.uid).isEmpty) {
            return ApiResponse(ResponseTokens.Login.registered)
        }

        userRepository.save(user)
        return ApiResponse(ResponseTokens.ok, "已注册成功", user)
    }

    /**
     * 登录
     */
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
        return ApiResponse(ResponseTokens.ok, "登录成功", UserCredit(user.get(), token))
    }

    /**
     * 自动登录
     */
    fun autoLogin(userAutoLoginForm: UserAutoLoginForm): ApiResponse<Any> {
        val userToken = userTokenRepository.findByUidAndToken(userAutoLoginForm.uid, userAutoLoginForm.token)
        val now = Timestamp.from(Instant.now())
        when {
            userToken.isEmpty -> {
                throw HttpStatusException(ApiResponse(ResponseTokens.Login.noToken))
            }
            now.after(userToken.get().expired) -> {
                throw HttpStatusException(ApiResponse(ResponseTokens.Login.tokenExpired))
            }
        }

        val newExpired = Timestamp.from(now.toInstant().plus(7, ChronoUnit.DAYS))
        userToken.get().expired = newExpired
        userTokenRepository.save(userToken.get())
        val user = userRepository.findById(userAutoLoginForm.uid).get()
        return ApiResponse(ResponseTokens.ok, "自动登录成功", UserCredit(user, userToken.get()))
    }

}