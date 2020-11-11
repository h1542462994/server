package org.tty.server.repository

import org.springframework.data.repository.CrudRepository
import org.tty.server.model.entity.UserToken

interface UserTokenRepository : CrudRepository<UserToken, Int>