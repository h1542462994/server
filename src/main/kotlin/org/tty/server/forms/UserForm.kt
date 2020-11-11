package org.tty.server.forms

import org.tty.server.forms.pattern.Patterns
import javax.validation.constraints.*


class UserForm: UserLoginForm() {

    @NotNull
    @Size(min = 2, max = 20)
    lateinit var name: String

    @NotNull
    @Email
    lateinit var email: String
}