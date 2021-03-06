package com.kitchenforce.service.menu

import com.kitchenforce.common.exception.NotFoundException
import com.kitchenforce.domain.menus.* // ktlint-disable no-wildcard-imports
import com.kitchenforce.domain.products.entities.ProductRepository
import com.kitchenforce.dto.menus.MenuCreateRequestDto
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.lang.IllegalStateException
import javax.transaction.Transactional

@Service
class MenuService(
    private val menuRepository: MenuRepository,
    private val menuGroupRepository: MenuGroupRepository,
    private val productRepository: ProductRepository,
    private val menuProductRepository: MenuProductRepository
) {

    @Transactional
    fun createMenu(req: MenuCreateRequestDto, menuGroup: Int) {
        val group: MenuGroup = menuGroupRepository.findByIdOrNull(menuGroup) ?: throw IllegalStateException("MENU GROUP NOT FOUND")
        val menu: Menu = menuRepository.save(Menu(null, req.name, req.price, menuGroup = group))
        req.products.forEach {
            menuProductRepository.save(
                MenuProduct(
                    null,
                    it.number,
                    menu,
                    productRepository.findByIdOrNull(it.productId) ?: throw IllegalStateException("PRODUCT NOT FOUND")
                )
            )
        }
    }

    fun findAll(): List<Menu> {
        return menuRepository.findAll()
    }

    fun findById(id: Int): Menu {
        return menuRepository.findByIdOrNull(id)
            ?: throw NotFoundException("메뉴 $id 를 찾을 수 없습니다.")
    }
}
