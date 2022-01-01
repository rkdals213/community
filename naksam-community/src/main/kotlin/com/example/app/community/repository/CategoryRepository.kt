package com.example.app.community.repository

import com.example.app.community.domain.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull

fun CategoryRepository.findByIdWithCheck(id: Long): Category = findByIdOrNull(id)
    ?: throw RuntimeException("카테고리가 존재하지 않습니다. id: $id")

interface CategoryRepository : JpaRepository<Category, Long>
