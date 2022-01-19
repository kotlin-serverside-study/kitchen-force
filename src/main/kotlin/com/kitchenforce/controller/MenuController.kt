package com.kitchenforce.controller

import com.kitchenforce.domain.menus.Menu
import com.kitchenforce.service.MenuService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/menus")
@RestController
class MenuController(
    private val menuService: MenuService,
) {
    @GetMapping("/")
    fun index(): List<Menu> {
        return menuService.findAll();
    }
}