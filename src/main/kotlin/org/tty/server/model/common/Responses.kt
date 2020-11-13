package org.tty.server.model.common


object Responses {
    fun validateError(msg: String): ApiResponse<Any> {
        return ApiResponse(ResponseTokens.validateError, msg, null )
    }

}

