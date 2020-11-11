package org.tty.server.exception

import org.springframework.http.HttpStatus
import org.tty.server.model.uni.ApiResponse
import java.lang.Exception

class HttpStatusException(var response: ApiResponse<Any>): Exception("return Failed HttpStatusCode")