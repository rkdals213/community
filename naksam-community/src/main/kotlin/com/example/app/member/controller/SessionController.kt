package com.example.app.member.controller

import com.example.app.member.dto.LoginForm
import com.example.app.member.service.SessionService
import com.example.common.config.WebConfig.Companion.EXPIRATION
import com.example.common.config.WebConfig.Companion.JWT_COOKIE_NAME
import com.example.common.security.CookieConfig
import com.example.common.security.HttpSupport
import com.example.common.security.JwtServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

const val EXPIRATION = 60 * 60 * 24

@RestController
@RequestMapping("/session")
class SessionController(
    private val sessionService: SessionService,
    private val jwtServiceImpl: JwtServiceImpl
) {

    @PostMapping("/login")
    fun login(
        @RequestBody
        loginForm: LoginForm,
        req: HttpServletRequest,
        res: HttpServletResponse
    ): ResponseEntity<Any> {
        val payload = sessionService.login(loginForm)
        val token = jwtServiceImpl.create(payload)

        val cookie = HttpSupport.createCookie(
            CookieConfig(
                name = JWT_COOKIE_NAME,
                value = token,
                expires = EXPIRATION,
                secure = "https" == req.scheme
            )
        )

        res.addCookie(cookie)

        return ResponseEntity.ok()
            .build()
    }
}