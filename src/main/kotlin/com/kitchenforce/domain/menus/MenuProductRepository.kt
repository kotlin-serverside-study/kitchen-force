package com.kitchenforce.domain.menus

import org.springframework.data.jpa.repository.JpaRepository

interface MenuProductRepository: JpaRepository<MenuProduct, Int> {
}