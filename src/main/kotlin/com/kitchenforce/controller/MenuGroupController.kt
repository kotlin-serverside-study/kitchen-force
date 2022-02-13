package com.kitchenforce.controller

import com.kitchenforce.domain.menus.MenuGroup
import com.kitchenforce.service.MenuGroupService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "MenuGroup")
@RequestMapping("/api/menu-groups")
@RestController
class MenuGroupController(
    private val menuGroupService: MenuGroupService
) {
    @Operation(summary = "메뉴 그룹 목록을 반환합니다.")
    @GetMapping("/")
    fun index(): List<MenuGroup> {
        return menuGroupService.findAll()
    }
}
