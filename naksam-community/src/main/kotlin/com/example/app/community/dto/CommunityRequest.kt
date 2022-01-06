package com.example.app.community.dto

import com.example.app.community.domain.Category
import com.example.app.community.domain.Community
import com.example.app.community.domain.CommunityImages
import com.example.app.community.domain.CommunityMemberIds
import com.example.common.infra.Location

data class CommunityCondition(
    var name: String,
    var categoryId: Long
)

data class CreateCommunityRequest(
    val categoryId: Long,
    val name: String,
    val maxMemberCount: Int,
    val location: Location,
    val communityImages: CommunityImages,
    val description: String
) {
    fun toEntity(category: Category): Community {
        return Community(
            name = name,
            category = category,
            communityMemberIds = CommunityMemberIds(),
            maxMemberCount = maxMemberCount,
            location = location,
            communityImages = communityImages,
            description = description
        )
    }
}