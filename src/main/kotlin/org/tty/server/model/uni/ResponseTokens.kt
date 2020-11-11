package org.tty.server.model.uni

object ResponseTokens {
    val validateError = ResponseToken(400, "validateError")
    val ok = ResponseToken(200, "success", "操作成功")
    object Login {
        val registered = ResponseToken(1, "registered", "当前用户已经被注册")
        val noUser = ResponseToken(403, "no_user", "不存在该用户")
        val passwordError = ResponseToken(403, "passwordError", "密码错误")
    }
}