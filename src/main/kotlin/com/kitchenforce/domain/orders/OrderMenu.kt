package com.kitchenforce.domain.orders

import com.kitchenforce.common.entity.AuditEntity
import com.kitchenforce.domain.menus.Menu
import com.kitchenforce.domain.orders.dto.OrderMenuDto
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class OrderMenu(

    @Column
    var quantity: Long,

    @ManyToOne
    @JoinColumn(name = "orders_id")
    var order: Order?,

    @ManyToOne
    @JoinColumn(name = "menu_id")
    var menu: Menu?,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
) : AuditEntity()
