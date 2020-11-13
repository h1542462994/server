package org.tty.server.common

import org.tty.server.forms.*
import org.tty.server.model.entity.User
import org.tty.server.model.entity.UserToken
import java.sql.Timestamp
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*

/**
 * 用户辅助工具类
 * @see User
 * @see UserForm
 */
object UserTool {
    fun toUserNormal(userForm: UserLoginForm): User {
        return if (userForm is UserForm){
            User(userForm.uid, User.NORMAL, User.GROUP_NORMAL, userForm.name, userForm.password, userForm.email)
        } else {
            User(userForm.uid, userForm.password)
        }
    }

    fun generateToken(user: User): UserToken {

        val token = UserToken()
        token.uid = user.uid
        token.token = UUID.randomUUID().toString() // 随机指定uuid为token
        var instant = Instant.now()
        token.created = Timestamp.from(instant)
        instant = instant.plus(7, ChronoUnit.DAYS) // 增加7天
        token.expired = Timestamp.from(instant)
        return token
    }
}