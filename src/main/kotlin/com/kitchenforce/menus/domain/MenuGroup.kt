package com.kitchenforce.menus.domain

import org.springframework.data.annotation.CreatedDate
import java.util.*
import javax.persistence.*

@Entity
class MenuGroup(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    @CreatedDate
    @Column(updatable = false, nullable = false)
    val createdAt: Date,

    @Column(nullable = false)
    val name: String
)