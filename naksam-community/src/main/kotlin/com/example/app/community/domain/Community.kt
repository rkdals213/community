package com.example.app.community.domain

import com.example.common.infra.BaseEntity
import com.example.common.infra.Location
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Lob
import javax.persistence.ManyToOne

@Entity
class Community(
    val name: String,

    @ManyToOne(fetch = FetchType.LAZY)
    val category: Category,

    val communityMemberIds: CommunityMemberIds,

    val maxMemberCount: Int,

    var location: Location,

    val communityImages: CommunityImages,

    @Lob
    val description: String,

    id: Long = 0L
) : BaseEntity(id) {

    fun ableToJoin() = communityMemberIds.count() < maxMemberCount

    fun join(memberId: Long) {
        ableToJoin()
        communityMemberIds.add(memberId)
    }
}