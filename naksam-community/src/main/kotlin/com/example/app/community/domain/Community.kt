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
    val communityMemberIds: CommunityMemberIds,

    var location: Location,

    val communityImages: CommunityImages,

    @Lob
    val description: String,

    id: Long = 0L
) : BaseEntity(id) {

    fun join(memberId: Long) {
        communityMemberIds.add(memberId)
    }

    fun withdrawal(memberId: Long) {
        communityMemberIds.withdrawal(memberId)
    }
}