package org.tty.server.forms

import org.tty.server.forms.pattern.Patterns
import javax.validation.constraints.*


class UserForm {
    @NotNull
    @Pattern(regexp = Patterns.digitNumberUnderLine)
    @Size(min = 2, max = 20)
    lateinit var uid: String

    @NotNull
    @Size(min = 2, max = 20)
    lateinit var name: String

    @NotNull
    @Size(min = 6, max = 128)
    lateinit var password: String

    @NotNull
    @Email
    lateinit var email: String
}