package com.kitchenforce.service

import com.kitchenforce.domain.menus.MenuGroup
import com.kitchenforce.domain.menus.MenuGroupRepository
import org.springframework.stereotype.Service

@Service
class MenuGroupService(
    private val menuGroupRepository: MenuGroupRepository
) {
    fun findAll(): List<MenuGroup> {
        return menuGroupRepository.findAll()
    }
}
