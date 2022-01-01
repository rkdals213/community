package com.example.app.community.repository

import com.example.app.community.domain.Category
import com.example.app.community.domain.Community
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull

fun CommunityRepository.findByIdWithCheck(id: Long) = findByIdOrNull(id) ?: throw NoSuchElementException("커뮤니티가 없습니다")

interface CommunityRepository : JpaRepository<Community, Long> {
    fun findByCategoryAndNameContains(categoryId: Category, name: String, pageable: Pageable): Page<Community>
}
