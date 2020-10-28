package org.tty.server.advice

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.validation.BindException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.tty.server.model.ApiResponse
import org.tty.server.model.Response
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
@ResponseBody
class GlobalExceptionHandler {

    val logger: Logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(BindException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun defaultErrorHandler(request: HttpServletRequest, exception: Exception) : ApiResponse<Int> {
        if (exception is BindException){
            return Response.validateError(exception.toString())
        }
        return Response.validateError("error")
    }
}