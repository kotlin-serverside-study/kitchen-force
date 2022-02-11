package com.kitchenforce.domain.orders

import com.kitchenforce.common.entity.AuditEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "orders")
class Order(
    @Column
    var orderType: String,

    @Column
    var paymentPrice: Long,

    @Column
    var paymentMethod: String,

    @Column
    var requirement: String,

    @Column
    var deliveryAddress: String,

    @ManyToOne
    @JoinColumn(name = "order_table_id")
    var orderTable: OrderTable,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

) : AuditEntity() {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    lateinit var orderMenuList: List<OrderMenu>
}
