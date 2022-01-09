package com.kitchenforce.domain.menus

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*
import javax.persistence.*

@Entity
@Table
@EntityListeners(AuditingEntityListener::class)
class MenuProduct(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,

    @CreatedDate
    @Column(updatable = false, nullable = false)
    val createdAt: Date,

    @Column(nullable = false)
    val quantity: Int,

    @ManyToOne
    @JoinColumn(name = "menu_id")
    val menu: Menu,

    // TODO: Product 구현시 추가 예정
//    val product: Product,
)