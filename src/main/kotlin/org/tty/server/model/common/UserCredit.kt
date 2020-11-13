package org.tty.server.model.common

import org.tty.server.model.entity.User
import org.tty.server.model.entity.UserToken

data class UserCredit(val user: User, val token: UserToken)