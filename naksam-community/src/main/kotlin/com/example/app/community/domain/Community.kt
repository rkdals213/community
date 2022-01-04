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

    val maxMemberCount: Int,

    var location: Location,

    val communityImages: CommunityImages,

    @Lob
    val description: String,

    id: Long = 0L
) : BaseEntity(id) {

    fun join(memberId: Long) {
        check(ableToJoin(memberId)) { "이미 가입했거나 최대 가입 가능한 인원을 초과했습니다" }
        communityMemberIds.add(memberId)
    }

    fun ableToJoin(memberId: Long) =
        communityMemberIds.count() < maxMemberCount && communityMemberIds.notContains(memberId)
}