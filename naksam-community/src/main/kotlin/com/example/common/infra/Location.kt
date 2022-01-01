package com.example.common.infra

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Location(
    @Column(nullable = false)
    val state: String,

    @Column(nullable = false)
    val city: String
)