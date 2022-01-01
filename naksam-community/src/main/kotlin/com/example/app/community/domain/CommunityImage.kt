package com.example.app.community.domain

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class CommunityImage(
    @Column(nullable = false)
    val url: String
)