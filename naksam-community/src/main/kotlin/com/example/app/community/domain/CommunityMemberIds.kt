package com.example.app.community.domain

import javax.persistence.CollectionTable
import javax.persistence.Column
import javax.persistence.ElementCollection
import javax.persistence.Embeddable

@Embeddable
class CommunityMemberIds(
    @CollectionTable(name = "community_member_ids")
    @ElementCollection
    @Column(name = "member_id")
    val memberIds: MutableSet<Long> = mutableSetOf()
) {
    fun count() = memberIds.size

    fun add(memberId: Long) {
        memberIds.add(memberId)
    }

    fun notContains(memberId: Long): Boolean =
        memberIds.contains(memberId)
            .not()
}