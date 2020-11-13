package org.tty.server.forms

import org.tty.server.forms.pattern.Patterns
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

class UserAutoLoginForm {
    @NotNull
    @Pattern(regexp = Patterns.digitNumberUnderLine)
    @Size(min = 2, max = 20)
    lateinit var uid: String

    @NotNull
    @Pattern(regexp = Patterns.token)
    lateinit var token: String
}