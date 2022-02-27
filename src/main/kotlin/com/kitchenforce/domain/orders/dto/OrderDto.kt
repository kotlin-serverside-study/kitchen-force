package com.kitchenforce.domain.orders.dto

import com.kitchenforce.domain.orders.Order

data class OrderDto(
    var orderType: String = "",
    var paymentMethod: String = "",
    var requirement: String = "",
    var deliveryAddress: String = "",
    var orderMenuDtoList: List<OrderMenuDto> = ArrayList()
)

fun Order.toDto() = OrderDto(
    orderType = orderType,
    paymentMethod = paymentMethod,
    requirement = requirement,
    deliveryAddress = deliveryAddress,
    orderMenuDtoList = orderMenuList.map {
        it.toDto()
    }
)
