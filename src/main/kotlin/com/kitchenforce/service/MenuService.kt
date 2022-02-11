package com.kitchenforce.service

import com.kitchenforce.common.exception.NotFoundException
import com.kitchenforce.domain.menus.Menu
import com.kitchenforce.domain.menus.MenuRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MenuService(
    private val menuRepository: MenuRepository
) {
    fun findAll(): List<Menu> {
        return menuRepository.findAll()
    }

    fun findById(id: Int): Menu {
        return menuRepository.findByIdOrNull(id)
            ?: throw NotFoundException("메뉴 $id 를 찾을 수 없습니다.")
    }
}
