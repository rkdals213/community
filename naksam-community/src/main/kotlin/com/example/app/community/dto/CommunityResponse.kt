package com.example.app.community.dto

import com.example.app.community.domain.Category
import com.example.app.community.domain.Community
import com.example.app.community.domain.CommunityImages
import com.example.common.infra.Location

data class CommunityResponse(
    var id: Long,
    var category: CategoryResponse,
    var maxMemberCount: Int,
    var location: Location,
    val communityImages: CommunityImages,
    val description: String
) {
    constructor(community: Community) : this(
        community.id,
        CategoryResponse(community.category),
        community.maxMemberCount,
        community.location,
        community.communityImages,
        community.description
    )
}

data class CategoryResponse(
    val id: Long,
    val name: String
) {
    constructor(category: Category) : this(
        category.id,
        category.name
    )
}