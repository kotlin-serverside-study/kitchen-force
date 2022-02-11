package com.kitchenforce.domain.menus

import com.kitchenforce.common.entity.AuditEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "menu")
class Menu(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val price: Int,

    @Column(nullable = false)
    val isHidden: Boolean,

    @ManyToOne
    @JoinColumn(name = "menu_group_id")
    val menuGroup: MenuGroup,

) : AuditEntity()
