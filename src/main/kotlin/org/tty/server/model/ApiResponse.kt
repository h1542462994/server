package org.tty.server.model

object Response {
    fun validateError(msg: String): ApiResponse<Int> {
        return ApiResponse(validateError, "validateError", msg, null )
    }

    const val validateError = 400
}

data class ApiResponse<T>(
        var code: Int,
        var summary: String,
        var msg: String,
        var data: T?) {
}
