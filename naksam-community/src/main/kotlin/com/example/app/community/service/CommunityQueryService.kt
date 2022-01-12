package com.example.app.community.service

import com.example.app.community.dto.CommunityCondition
import com.example.app.community.dto.CommunityResponse
import com.example.app.community.dto.MemberInCommunity
import com.example.app.community.repository.CategoryRepository
import com.example.app.community.repository.CommunityRepository
import com.example.app.community.repository.findByIdWithCheck
import com.example.app.member.domain.Member
import com.example.app.member.repository.MemberRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CommunityQueryService(
    private val communityRepository: CommunityRepository,
    private val categoryRepository: CategoryRepository,
    private val memberRepository: MemberRepository
) {
    fun findByCondition(communityCondition: CommunityCondition, pageable: Pageable): Page<CommunityResponse> {
        val category = categoryRepository.findByIdWithCheck(communityCondition.categoryId)

        return communityRepository.findByCategoryAndNameContains(category, communityCondition.name, pageable)
            .map { CommunityResponse(it) }
    }

    fun findDetail(id: Long): CommunityResponse = CommunityResponse(communityRepository.findByIdWithCheck(id))

    fun membersOfCommunity(member: Member, id: Long): List<MemberInCommunity> {
        val community = communityRepository.findByIdWithCheck(id)
        require(community.memberInCommunity(member)) { "가입한 사용자만 조회할 수 있습니다" }
        val members = memberRepository.findAllById(community.communityMemberIds.memberIds)
        return members.map { MemberInCommunity(it) }
    }
}