package com.example.common.config

import com.example.common.security.JwtService
import com.example.common.security.JwtSessionArgumentResolver
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class WebConfig(
    private val jwtService: JwtService,
    private val jwtArgsResolver: JwtSessionArgumentResolver
) : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(jwtInterceptor())
    }

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(jwtArgsResolver)
    }

    @Bean
    fun jwtInterceptor(): JwtInterceptor {
        return JwtInterceptor(jwtService, JWT_COOKIE_NAME)
    }

    companion object {
        const val JWT_COOKIE_NAME = "my.test.jwt"
        const val EXPIRATION = 60 * 60 * 24
    }
}