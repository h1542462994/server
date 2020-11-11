package org.tty.server.model.uni

data class ApiResponse<T>(
        var code: Int,
        var summary: String,
        var msg: String,
        var data: T? = null) {
    constructor(token: ResponseToken, msg: String, data: T? = null) : this(token.code, token.summary, msg, data)
    constructor(token: ResponseToken, data: T? = null): this(token.code, token.summary, token.defaultMsg, data)
}