package com.kitchenforce.controller.order

import com.kitchenforce.domain.orders.dto.OrderTableDto
import com.kitchenforce.service.order.OrderTableService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.* // ktlint-disable no-wildcard-imports
import javax.validation.constraints.Min

@Validated
@RestController
@RequestMapping("/api/order-table")
class OrderTableController(
    private val orderTableService: OrderTableService
) {

    @GetMapping("")
    fun getOrderTableList(): List<OrderTableDto> {
        return orderTableService.getAll()
    }

    @GetMapping("/{tableName}")
    fun getTableInfo(@PathVariable tableName: String): OrderTableDto? {
        return orderTableService.get(tableName)
    }

    @PutMapping("/{numberOfGuests}/{tableName}")
    fun updateNumberOfGuests(@PathVariable @Min(0) numberOfGuests: Int, @PathVariable tableName: String) {
        orderTableService.updateOccupiedTable(numberOfGuests, tableName)
    }
}
