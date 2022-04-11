package com.kitchenforce.service.product

import com.kitchenforce.common.exception.NotFoundException
import com.kitchenforce.common.utils.SlangDictionary
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
    val service: ProductService,
    val slangDictionary: SlangDictionary
) {

    @Nested
    @Transactional
    inner class ProductServiceCreateTest {
        @Test
        fun create_정상실행() {
            val vo = Product(
                name = "제품1",
                price = 100,
                slangDictionary = slangDictionary
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
                slangDictionary = slangDictionary
            )
            assertThrows<ConstraintViolationException> {
                service.create(vo)
            }
        }
    }

    @Nested
    @Transactional
    inner class ProductUpdateService {
        @Test
        fun update_정상_실행() {
            val created = service.create(Product(name = "제품1", price = 100, slangDictionary = slangDictionary))
            service.update(created.id!!, Product(name = created.name, price = 999, slangDictionary = slangDictionary))

            val list = service.findAll()
            assertEquals(list.size, 1)
            assertEquals(list.first().name, created.name)
            assertEquals(list.first().price, 999)
        }

        @Test
        fun update_인자의_id에_해당하는_상품이_없는_경우() {
            assertThrows<NotFoundException> {
                service.update(999, Product(name = "제품1", price = 100, slangDictionary = slangDictionary))
            }
        }
    }
}