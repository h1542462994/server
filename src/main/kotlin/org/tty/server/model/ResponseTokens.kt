package org.tty.server.model

object ResponseTokens {
    val validateError = ResponseToken(400, "validateError")
    val ok = ResponseToken(200, "success", "操作成功")
    object Login {
        val registered = ResponseToken(1, "registered", "当前用户已经被注册")

    }
}