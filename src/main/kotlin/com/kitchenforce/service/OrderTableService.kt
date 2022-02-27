package com.kitchenforce.service

import com.kitchenforce.domain.menus.Menu
import com.kitchenforce.domain.menus.MenuRepository
import com.kitchenforce.domain.orders.Order
import com.kitchenforce.domain.orders.OrderMenu
import com.kitchenforce.domain.orders.OrderMenuRepository
import com.kitchenforce.domain.orders.OrderRepository
import com.kitchenforce.domain.orders.OrderTable
import com.kitchenforce.domain.orders.OrderTableRepository
import com.kitchenforce.domain.orders.dto.OrderTableDto
import com.kitchenforce.domain.orders.dto.toDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderTableService(
    private val orderTableRepository: OrderTableRepository,
    private val orderRepository: OrderRepository,
    private val orderMenuRepository: OrderMenuRepository,
    private val menuRepository: MenuRepository
) {

    @Transactional
    fun create(dto: OrderTableDto) {
        val orderTable: OrderTable = OrderTable(
            userId = dto.userId,
            name = dto.tableName,
            emptyness = dto.emptyness,
            numberOfGuests = dto.numberOfGuests
        )

        val savedOrder = orderTableRepository.save(orderTable)

        for (orderDto in dto.orderDtoList) {

            val order: Order = Order(
                orderType = orderDto.orderType,
                paymentPrice = 0,
                paymentMethod = orderDto.paymentMethod,
                requirement = orderDto.requirement,
                deliveryAddress = orderDto.deliveryAddress,
                orderTable = orderTable
            )
            orderRepository.save(order)

            val orderMenus: MutableList<OrderMenu> = ArrayList()
            for (orderMenuDto in orderDto.orderMenuDtoList) {

                val menu: Menu = menuRepository.findByName(orderMenuDto.menuName)
                val orderMenu: OrderMenu = OrderMenu(
                    quantity = orderMenuDto.quantity,
                    order = order,
                    menu = menu
                )
                orderMenus.add(orderMenu)
            }
            orderMenuRepository.saveAll(orderMenus)
        }
    }

    fun orderInfo(userId: Long): OrderTableDto {
        return orderTableRepository.findByUserId(userId)
            ?.let { it.toDto() }
            ?: return OrderTableDto(
                userId = -1,
                emptyness = true,
                tableName = "-1",
                numberOfGuests = -1
            )
    }
}
