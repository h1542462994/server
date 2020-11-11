package org.tty.server.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

/**
 * 重写安全配置，关闭授权和csrf请求
 */
@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity?) {
        // 不启动任何授权
        http!!.authorizeRequests()
                .anyRequest().permitAll().and().logout().permitAll()

        // 关闭csrf
        http.csrf().disable()
    }
}