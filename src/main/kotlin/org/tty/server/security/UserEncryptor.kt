package org.tty.server.security

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import org.tty.server.model.entity.User

@Component
class UserEncryptor {
    fun encrypt(user: User): User {
        val passwordEncoder = BCryptPasswordEncoder()
        val encryptedPassword = passwordEncoder.encode(user.password)
        user.password = encryptedPassword
        return user
    }

}