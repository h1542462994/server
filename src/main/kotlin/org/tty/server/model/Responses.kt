package org.tty.server.model


object Responses {
    fun validateError(msg: String): ApiResponse<Int> {
        return ApiResponse(ResponseTokens.validateError, msg, null )
    }

}

