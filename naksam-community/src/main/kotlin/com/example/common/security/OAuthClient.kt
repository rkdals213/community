package com.example.common.security

import org.springframework.stereotype.Component
import java.time.Instant
import java.time.ZoneOffset
import java.time.ZonedDateTime

@Component
class OAuthClient {
    class Token(
        var expiration: ZonedDateTime
    )

    fun createToken(): Token =
        Token(
            Instant.ofEpochSecond(Instant.now().epochSecond + 60 * 60 * 24)
                .atZone(ZoneOffset.UTC)
        )
}