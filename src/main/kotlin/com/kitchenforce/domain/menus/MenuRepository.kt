package com.kitchenforce.domain.menus

import org.springframework.data.jpa.repository.JpaRepository

interface MenuRepository : JpaRepository<Menu, Int> {

    fun findByName(name: String): Menu
}
