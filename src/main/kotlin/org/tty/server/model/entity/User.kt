package org.tty.server.model.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class User {
    @Id
    lateinit var uid: String /*用户Id*/
    var type: Long = NORMAL
    lateinit var groupType: String
    lateinit var name: String
    @JsonIgnore
    lateinit var password: String

    lateinit var email: String

    constructor()

    constructor(uid: String, password: String){
        this.uid = uid
        this.password = password
    }

    constructor(uid: String, type: Long, groupType: String, name: String, password: String, email: String) {
        this.uid = uid
        this.type = type
        this.groupType = groupType
        this.name = name
        this.password = password
        this.email = email
    }

    companion object {
        const val NORMAL = 0L
        const val ADMIN = 1L
        const val GROUP_NORMAL = "normal"
    }
}