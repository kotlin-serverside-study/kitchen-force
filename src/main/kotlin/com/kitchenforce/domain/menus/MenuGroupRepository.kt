package com.kitchenforce.domain.menus

import org.springframework.data.jpa.repository.JpaRepository

interface MenuGroupRepository: JpaRepository<MenuGroup, Int> {
}