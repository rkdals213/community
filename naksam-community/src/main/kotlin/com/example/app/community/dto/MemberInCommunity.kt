package com.example.app.community.dto

import com.example.app.member.domain.Member

data class MemberInCommunity(
    var id: Long,
    var name: String,
    var email: String,
    var phoneNumber: String
) {
    constructor(member: Member) : this(
        member.id,
        member.name,
        member.email,
        member.phoneNumber
    )
}