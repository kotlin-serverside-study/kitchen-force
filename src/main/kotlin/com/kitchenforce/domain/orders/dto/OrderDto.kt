package com.kitchenforce.domain.orders.dto

data class OrderDto(
    var orderType: String = "",
    var paymentMethod: String = "",
    var requirement: String = "",
    var deliveryAddress: String = "",
    var orderMenuDtoList: List<OrderMenuDto> = ArrayList()
)
