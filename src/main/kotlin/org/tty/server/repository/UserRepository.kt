package org.tty.server.repository

import org.springframework.data.repository.CrudRepository
import org.tty.server.model.entity.User
import java.util.*

interface UserRepository : CrudRepository<User, String> {
    fun findByUidAndPassword(uid: String, password: String): Optional<User>
}