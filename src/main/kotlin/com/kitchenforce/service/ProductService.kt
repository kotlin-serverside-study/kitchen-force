package com.kitchenforce.service

import com.kitchenforce.common.exception.NotFoundException
import com.kitchenforce.domain.products.entities.Product
import com.kitchenforce.domain.products.entities.ProductRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductService(
    private val productRepository: ProductRepository,
) {
    fun findAll(): List<Product> {
        return productRepository.findAll()
    }

    private fun findById(id: Long): Product {
        return productRepository.findByIdOrNull(id)
            ?: throw NotFoundException("상품 $id 를 찾을 수 없습니다.")
    }

    @Transactional
    fun create(vo: Product): Product {
        return productRepository.save(vo)
    }
}
