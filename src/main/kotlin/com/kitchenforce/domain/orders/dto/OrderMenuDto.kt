package com.kitchenforce.domain.orders.dto

import com.kitchenforce.domain.orders.OrderMenu

data class OrderMenuDto(
    var quantity: Long = 0,
    var menuName: String = ""
)

fun OrderMenu.toDto() = OrderMenuDto(
    quantity = quantity,
    menuName = menu?.name ?: "메뉴 네임 미정",
)
