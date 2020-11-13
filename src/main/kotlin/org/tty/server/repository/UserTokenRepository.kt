package org.tty.server.repository

import org.springframework.data.repository.CrudRepository
import org.tty.server.model.entity.UserToken
import java.util.*

interface UserTokenRepository : CrudRepository<UserToken, Int> {
    fun findByUidAndToken(uid: String, token: String) : Optional<UserToken>
}