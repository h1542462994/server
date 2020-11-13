package org.tty.server.exception

import org.tty.server.model.common.ApiResponse
import java.lang.Exception

class HttpStatusException(var response: ApiResponse<Any>): Exception("return Failed HttpStatusCode")