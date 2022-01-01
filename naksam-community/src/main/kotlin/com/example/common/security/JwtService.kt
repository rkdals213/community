package com.example.common.security

import java.time.LocalDateTime

interface JwtService {
    fun create(payload: Payload): String
    fun isUsable(token: String): Boolean
    fun parseClaim(token: String): String
}

class MemberPayload(
    val id: Long,
    val email: String
)

class Payload(
    val exp: LocalDateTime,
    memberPayload: MemberPayload
) {
    val claims: MutableMap<String, Any> = mutableMapOf()

    init {
        addClaim("info", memberPayload)
    }

    fun addClaim(key: String, value: Any) {
        claims[key] = value
    }
}