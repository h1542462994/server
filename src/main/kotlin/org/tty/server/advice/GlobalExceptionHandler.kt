package org.tty.server.advice

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.validation.BindException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.tty.server.exception.HttpStatusException
import org.tty.server.model.common.ApiResponse
import org.tty.server.model.common.Responses
import javax.servlet.http.HttpServletRequest

/**
 * 全局异常处理器，用于处理字段验证错误。
 */
@RestControllerAdvice
@ResponseBody
class GlobalExceptionHandler {

    val logger: Logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(BindException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun defaultErrorHandler(request: HttpServletRequest, exception: Exception) : ApiResponse<Any> {
        logger.warn(exception.localizedMessage)
        return if (exception is BindException){
            Responses.validateError(exception.toString())
        } else {
            Responses.validateError("error")
        }
    }

    @ExceptionHandler(HttpStatusException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun httpStatusErrorHandler(request: HttpServletRequest, exception: Exception) : ApiResponse<Any> {
        logger.warn(exception.localizedMessage)
        return if (exception is HttpStatusException) {
            exception.response
        } else {
            Responses.validateError("error")
        }
    }
}