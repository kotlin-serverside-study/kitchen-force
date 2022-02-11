package com.kitchenforce.domain.orders

import com.kitchenforce.common.entity.AuditEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class OrderTable(

    @Column
    var userId: Long,

    @Column
    var name: String,

    @Column
    var emptyness: Boolean,

    @Column
    var numberOfGuests: Int,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
) : AuditEntity() {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderTable")
    lateinit var orderList: List<Order>
}
