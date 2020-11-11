package org.tty.server.model.uni


object Responses {
    fun validateError(msg: String): ApiResponse<Any> {
        return ApiResponse(ResponseTokens.validateError, msg, null )
    }

}

