package org.tty.server.seeder

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.tty.server.model.entity.User
import org.tty.server.repository.UserRepository
import org.tty.server.security.UserEncryptor

@Component
class DataBaseSeeder {
    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var userEncryptor: UserEncryptor

    fun insertData() {
        if (userRepository.findById("admin").isEmpty) {
            userRepository.save(
                    User(uid = "admin", type = User.NORMAL, groupType = User.GROUP_NORMAL, name = "admin", password = "admin_password", email = "admin@outlook.com" )
                            .apply { userEncryptor.encrypt(this) }
            )
        }

        if (userRepository.findById("test").isEmpty) {
            userRepository.save(
                    User(uid = "test", type = User.NORMAL, groupType = User.GROUP_NORMAL, name = "test", password = "test_password", email = "test@outlook.com" )
                            .apply { userEncryptor.encrypt(this) }
            )
        }
    }
}