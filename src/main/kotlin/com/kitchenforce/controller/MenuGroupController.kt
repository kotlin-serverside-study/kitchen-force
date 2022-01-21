package com.kitchenforce.controller

import com.kitchenforce.domain.menus.MenuGroup
import com.kitchenforce.service.MenuGroupService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/menu-groups")
@RestController
class MenuGroupController(
    private val menuGroupService: MenuGroupService
) {
    @GetMapping("/")
    fun index(): List<MenuGroup> {
        return menuGroupService.findAll();
    }
}