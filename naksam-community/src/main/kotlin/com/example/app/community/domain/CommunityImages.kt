package com.example.app.community.domain

import javax.persistence.CollectionTable
import javax.persistence.ElementCollection
import javax.persistence.Embeddable
import javax.persistence.FetchType

@Embeddable
class CommunityImages(
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "community_images")
    val images: MutableList<CommunityImage> = mutableListOf()
)