package com.thoughtmechanix.authentication

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
@EnableResourceServer
@EnableAuthorizationServer
open class Application {
    @RequestMapping(
            value = *arrayOf("/user"),
            produces = arrayOf("application/json"))
    fun user(user: OAuth2Authentication): Map<String, Any> =
            hashMapOf<String, Any>(
                "user" to user.userAuthentication.principal,
                "authorities" to AuthorityUtils.authorityListToSet(user.userAuthentication.authorities)
            )
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
