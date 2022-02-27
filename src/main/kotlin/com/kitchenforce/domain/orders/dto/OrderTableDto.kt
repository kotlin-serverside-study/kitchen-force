package com.kitchenforce.domain.orders.dto

import com.kitchenforce.domain.orders.OrderTable

data class OrderTableDto(
    var userId: Long = 0,
    var emptyness: Boolean = true,
    var tableName: String = "",
    var numberOfGuests: Int = 0,
    var orderDtoList: List<OrderDto> = ArrayList()
)

fun OrderTable.toDto() = OrderTableDto(
    userId = userId,
    emptyness = emptyness,
    tableName = name,
    numberOfGuests = numberOfGuests,
    orderDtoList = orderList.map {
        it.toDto()
    }.toList(),
)