package com.example.app.community.service

import com.example.app.community.dto.CreateCommunityRequest
import com.example.app.community.repository.CategoryRepository
import com.example.app.community.repository.CommunityRepository
import com.example.app.community.repository.findByIdWithCheck
import com.example.app.member.domain.Member
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CommunityService(
    private val communityRepository: CommunityRepository,
    private val categoryRepository: CategoryRepository
) {
    fun createCommunity(member: Member, createCommunityRequest: CreateCommunityRequest): Long {
        val category = categoryRepository.findByIdWithCheck(createCommunityRequest.categoryId)

        val community = createCommunityRequest.toEntity(category)

        community.join(member.id)

        return communityRepository.save(community)
            .id
    }

    fun joinCommunity(member: Member, communityId: Long) {
        val community = communityRepository.findByIdWithCheck(communityId)

        community.join(member.id)
    }
}