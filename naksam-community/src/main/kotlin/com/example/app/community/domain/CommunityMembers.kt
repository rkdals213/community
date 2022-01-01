package com.example.app.community.domain

import javax.persistence.Embeddable
import javax.persistence.OneToMany

@Embeddable
class CommunityMembers(
    @OneToMany
    val communityMembers: MutableList<CommunityMember> = mutableListOf()
) {
    fun count() = communityMembers.size

    fun add(communityMember: CommunityMember) {
        communityMembers.add(communityMember)
    }
}