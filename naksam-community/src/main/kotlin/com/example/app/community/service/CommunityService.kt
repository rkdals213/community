package com.example.app.community.service

import com.example.app.community.domain.CommunityMember
import com.example.app.community.dto.CreateCommunityRequest
import com.example.app.community.repository.CategoryRepository
import com.example.app.community.repository.CommunityMemberRepository
import com.example.app.community.repository.CommunityRepository
import com.example.app.community.repository.findByIdWithCheck
import com.example.app.member.domain.Member
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CommunityService(
    private val communityRepository: CommunityRepository,
    private val communityMemberRepository: CommunityMemberRepository,
    private val categoryRepository: CategoryRepository
) {
    fun createCommunity(member: Member, createCommunityRequest: CreateCommunityRequest): Long {
        val category = categoryRepository.findByIdWithCheck(createCommunityRequest.categoryId)

        val community = createCommunityRequest.toEntity(category)

        val communityMember = CommunityMember(community, member)
        communityMemberRepository.save(communityMember)

        community.join(communityMember)

        return communityRepository.save(community)
            .id
    }
}