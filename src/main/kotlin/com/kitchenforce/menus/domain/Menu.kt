package com.kitchenforce.menus.domain

import org.springframework.data.annotation.CreatedDate
import java.util.*
import javax.persistence.*

@Entity
class Menu(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    @CreatedDate
    @Column(updatable = false, nullable = false)
    val createdAt: Date,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val price: Int,

    @Column(nullable = false)
    val isHidden: Boolean,

    @ManyToOne
    @JoinColumn(name = "menu_group_id")
    val menuGroup: MenuGroup,
)