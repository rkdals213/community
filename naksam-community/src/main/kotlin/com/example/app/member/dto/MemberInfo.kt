package com.example.app.member.dto

import com.example.common.security.MemberPayload

data class MemberInfo(
    val id: Long,
    val email: String,
    val name: String
) {
    constructor(memberPayload: MemberPayload) : this(
        memberPayload.id,
        memberPayload.email,
        memberPayload.name
    )
}