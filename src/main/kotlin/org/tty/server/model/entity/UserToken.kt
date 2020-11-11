package org.tty.server.model.entity

import java.sql.Timestamp
import javax.persistence.*

@Entity
class UserToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null
    lateinit var uid: String
    lateinit var token: String
    lateinit var created: Timestamp
    lateinit var expired: Timestamp
}