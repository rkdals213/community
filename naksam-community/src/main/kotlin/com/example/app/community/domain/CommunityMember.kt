package com.example.app.community.domain

import com.example.app.member.domain.Member
import com.example.common.infra.BaseEntity
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Entity
class CommunityMember(
    @ManyToOne(fetch = FetchType.LAZY)
    val community: Community,

    @ManyToOne(fetch = FetchType.LAZY)
    val member: Member,

    id: Long = 0L
) : BaseEntity(id){
}