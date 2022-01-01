package com.example.app.community.domain

import com.example.common.infra.BaseEntity
import javax.persistence.Entity

@Entity
class Category(
    val name: String,

    id: Long = 0L
) : BaseEntity(id) {
}