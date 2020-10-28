package org.tty.server.converter

import org.tty.server.forms.*
import org.tty.server.model.*

class UserConverter {
    fun toUserNormal(userForm: UserForm): User {
        return User(userForm.uid, User.NORMAL, User.GROUP_NORMAL, userForm.name, userForm.password, userForm.email)
    }
}