package com.example.app.community.domain

import com.example.common.infra.BaseEntity
import com.example.common.infra.Location
import javax.persistence.*

@Entity
class Community(
    val name: String,

    @ManyToOne(fetch = FetchType.LAZY)
    val category: Category,

    @Embedded
    val communityMembers: CommunityMembers,

    val maxMemberCount: Int,

    @Embedded
    var location: Location,

    @Embedded
    val communityImages: CommunityImages,

    @Lob
    val description: String,

    id: Long = 0L
) : BaseEntity(id) {

    fun ableToJoin() = communityMembers.count() < maxMemberCount

    fun join(communityMember: CommunityMember) {
        ableToJoin()
        communityMembers.add(communityMember)
    }
}