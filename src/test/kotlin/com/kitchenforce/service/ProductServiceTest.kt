package com.kitchenforce.service

import com.kitchenforce.domain.products.entities.Product
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import javax.validation.ConstraintViolationException

@SpringBootTest
@ActiveProfiles("test")
internal class ProductServiceTest @Autowired constructor(
    private val service: ProductService
) {

    @Nested
    @Transactional
    inner class ProductServiceCreateTest {
        @Test
        fun create_정상실행() {
            val vo = Product(
                name = "제품1",
                price = 100,
            )
            service.create(vo)

            val list = service.findAll()
            assertEquals(list.size, 1)
            assertEquals(list.first().name, vo.name)
        }

        @Test
        fun create_가격_음수() {
            val vo = Product(
                name = "제품1",
                price = -1,
            )
            assertThrows<ConstraintViolationException> {
                service.create(vo)
            }
        }
    }
}
