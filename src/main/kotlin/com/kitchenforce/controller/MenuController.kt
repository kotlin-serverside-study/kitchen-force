package com.kitchenforce.controller

import com.kitchenforce.domain.menus.Menu
import com.kitchenforce.service.MenuService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Menu")
@RequestMapping("/api/menus")
@RestController
class MenuController(
    private val menuService: MenuService,
) {
    @Operation(summary = "메뉴 목록을 반환합니다.")
    @GetMapping("/")
    fun index(): List<Menu> {
        return menuService.findAll()
    }
}
