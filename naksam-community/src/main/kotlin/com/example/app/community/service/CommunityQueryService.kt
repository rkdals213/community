package com.example.app.community.service

import com.example.app.community.dto.CommunityCondition
import com.example.app.community.dto.CommunityResponse
import com.example.app.community.repository.CategoryRepository
import com.example.app.community.repository.CommunityRepository
import com.example.app.community.repository.findByIdWithCheck
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CommunityQueryService(
    private val communityRepository: CommunityRepository,
    private val categoryRepository: CategoryRepository
) {
    fun findByCondition(communityCondition: CommunityCondition, pageable: Pageable): Page<CommunityResponse> {
        val category = categoryRepository.findByIdWithCheck(communityCondition.categoryId)

        return communityRepository.findByCategoryAndNameContains(category, communityCondition.name, pageable)
            .map { CommunityResponse(it) }
    }

    fun findDetail(id: Long): CommunityResponse = CommunityResponse(communityRepository.findByIdWithCheck(id))

}