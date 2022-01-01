package com.example.app.community.repository

import com.example.app.community.domain.CommunityMember
import org.springframework.data.jpa.repository.JpaRepository

interface CommunityMemberRepository : JpaRepository<CommunityMember, Long> {
}