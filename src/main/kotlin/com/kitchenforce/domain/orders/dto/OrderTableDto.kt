package com.kitchenforce.domain.orders.dto

data class OrderTableDto(
    var userId: Long = 0,
    var emptyness: Boolean = true,
    var tableName: String = "",
    var numberOfGuests: Int = 0,
    var orderDtoList: MutableList<OrderDto> = ArrayList()
)
