package org.tty.server.repository

import org.springframework.data.repository.CrudRepository
import org.tty.server.model.User

interface UserRepository : CrudRepository<User, String> {
}