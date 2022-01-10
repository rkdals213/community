package com.example.app.community.domain

import javax.persistence.CollectionTable
import javax.persistence.Column
import javax.persistence.ElementCollection
import javax.persistence.Embeddable

@Embeddable
class CommunityMemberIds(
    val maxMemberCount: Int,

    @CollectionTable(name = "community_member_ids")
    @ElementCollection
    @Column(name = "member_id")
    val memberIds: MutableSet<Long> = mutableSetOf()
) {
    fun add(memberId: Long) {
        check(ableToJoin(memberId)) { "이미 가입했거나 최대 가입 가능한 인원을 초과했습니다" }
        memberIds.add(memberId)
    }

    fun ableToJoin(memberId: Long) = memberIds.size < maxMemberCount && memberIds.notContains(memberId)

    fun withdrawal(memberId: Long) {
        check(memberIds.contains(memberId)) { "가입한 회원이 아닙니다" }
        memberIds.remove(memberId)
    }

    fun memberCount(): Int = memberIds.size

    private fun MutableSet<Long>.notContains(value: Long) = contains(value).not()
}