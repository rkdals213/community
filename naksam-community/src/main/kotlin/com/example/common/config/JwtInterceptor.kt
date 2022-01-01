package com.example.common.config

import com.example.common.security.Authenticated
import com.example.common.security.HttpSupport
import com.example.common.security.JwtService
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JwtInterceptor(private val jwtService: JwtService, private val COOKIE_KEY: String) : HandlerInterceptor {
    override fun preHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any
    ): Boolean {
        if (handler !is HandlerMethod || !isAuthenticationPresent(handler)) {
            return true
        }
        val cookie = HttpSupport.getCookie(request, COOKIE_KEY).value

        return jwtService.isUsable(cookie)
    }

    private fun isAuthenticationPresent(handler: HandlerMethod): Boolean {
        return (handler.hasMethodAnnotation(Authenticated::class.java)
                || handler.beanType.isAnnotationPresent(Authenticated::class.java))
    }
}