package com.example.common.security

import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class HttpSupport {
    companion object {
        fun getCookie(req: HttpServletRequest, name: String): Cookie = req.cookies
            .toCollection(mutableListOf())
            .first { it.name == name && it.value.isNotEmpty() }

        fun createCookie(cookieConfig: CookieConfig) = cookieConfig.create()

        fun removeCookie(cookie: Cookie, httpServletResponse: HttpServletResponse) {
            val removed = CookieConfig(
                name = cookie.name,
                value = "",
                expires = 10,
                secure = cookie.secure
            ).create()
            httpServletResponse.addCookie(removed)
        }
    }
}

class CookieConfig(
    val name: String,
    val value: String,
    val expires: Int,
    val secure: Boolean
) {
    fun create(): Cookie {
        val cookie = Cookie(name, value)
        cookie.maxAge = expires;
        cookie.secure = secure;
        cookie.isHttpOnly = true;
        cookie.path = "/";
        return cookie;
    }
}