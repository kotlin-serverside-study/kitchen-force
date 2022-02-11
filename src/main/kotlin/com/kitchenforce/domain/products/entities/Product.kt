package com.kitchenforce.domain.products.entities

import com.kitchenforce.common.entity.AuditEntity
import com.kitchenforce.common.utils.SlangDictionary
import com.kitchenforce.domain.products.exception.ProductErrorCodeType
import com.kitchenforce.domain.products.exception.ProductException
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EntityListeners
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.PositiveOrZero

@Entity
@Table
@EntityListeners(AuditingEntityListener::class)
class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column
    val name: String,

    @Column
    @field:PositiveOrZero(message = "상품의 가격은 0보다 작을 수 없습니다.")
    val price: Int

) : AuditEntity() {
    init {
        checkValidProductName()
    }

    private fun checkValidProductName() {
        if (SlangDictionary.isSlang(name))
            throw ProductException(ProductErrorCodeType.INVALID_PRODUCT_NAME)
    }
}
