package com.kitchenforce.domain.orders

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import javax.persistence.EntityManager

@DataJpaTest
class OrderTableTest @Autowired constructor(
    val em : EntityManager
) {

    @Test
    @DisplayName("주문 테이블을 등록할 수 있다.")
    fun createOrderTableTest(){



    }


}