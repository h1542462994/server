package org.tty.server.model.common

object ResponseTokens {
    val validateError = ResponseToken(400, "validateError")
    val ok = ResponseToken(200, "success", "操作成功")
    object Login {
        val registered = ResponseToken(1, "registered", "当前用户已经被注册")
        val noUser = ResponseToken(2, "no_user", "不存在该用户")
        val passwordError = ResponseToken(3, "passwordError", "密码错误")
        val noToken = ResponseToken(4, "no_token", "不存在此token")
        val tokenExpired = ResponseToken(5, "token_expired", "token已过期")
    }
}