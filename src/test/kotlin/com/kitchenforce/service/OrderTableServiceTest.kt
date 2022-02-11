package com.kitchenforce.service

import com.kitchenforce.domain.menus.Menu
import com.kitchenforce.domain.menus.MenuGroup
import com.kitchenforce.domain.menus.MenuGroupRepository
import com.kitchenforce.domain.menus.MenuRepository
import com.kitchenforce.domain.orders.Order
import com.kitchenforce.domain.orders.OrderMenu
import com.kitchenforce.domain.orders.OrderMenuRepository
import com.kitchenforce.domain.orders.OrderRepository
import com.kitchenforce.domain.orders.OrderTable
import com.kitchenforce.domain.orders.OrderTableRepository
import com.kitchenforce.domain.orders.dto.OrderDto
import com.kitchenforce.domain.orders.dto.OrderMenuDto
import com.kitchenforce.domain.orders.dto.OrderTableDto
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional

// 테스트를 위한 상수
const val TEST_NUM_OF_GUEST = 1
const val TEST_USER_ID = 1L

@SpringBootTest
@ActiveProfiles("test")
class OrderTableServiceTest @Autowired constructor(
    private val orderTableService: OrderTableService,
    private val orderTableRepository: OrderTableRepository,
    private val orderRepository: OrderRepository,
    private val orderMenuRepository: OrderMenuRepository,
    private val menuRepository: MenuRepository,
    private val menuGroupRepository: MenuGroupRepository
) {

    val testMenuGroup = MenuGroup(
        id = null,
        name = "메뉴그룹1"
    )

    val testMenu = Menu(
        id = null,
        name = "메뉴1",
        price = 1_000,
        isHidden = false,
        menuGroup = testMenuGroup
    )

    @BeforeEach
    fun setUpMenus() {
        menuGroupRepository.save(testMenuGroup)
        menuRepository.save(testMenu)
    }

    @Test
    @DisplayName("주문이 들어오면 OrderTable의 상태를 추가하고, Order 정보를 추가한다.")
    @Transactional
    fun createTest1() {

        val testOrderMenuDto = OrderMenuDto(
            quantity = 1L,
            menuName = "메뉴1"
        )
        val testOrderDto = OrderDto(
            orderType = "배달",
            paymentMethod = "카드결제",
            requirement = "?",
            deliveryAddress = "사랑시 고백구 행복동",
            orderMenuDtoList = mutableListOf(testOrderMenuDto)
        )
        val testOrderTableDto = OrderTableDto(
            userId = TEST_USER_ID,
            emptyness = false,
            tableName = "sample-Table",
            numberOfGuests = TEST_NUM_OF_GUEST,
            orderDtoList = mutableListOf(testOrderDto)
        )

        assertDoesNotThrow { orderTableService.create(testOrderTableDto) }

        val savedOrder = orderRepository.findAll().firstOrNull()
        val savedOrderTable = orderTableRepository.findByUserId(1L)
        val savedOrderMenu = orderMenuRepository.findAll().firstOrNull()

        assertAll(
            { assertNotNull(savedOrder) },
            { assertNotNull(savedOrder) },
            { assertEquals(testOrderDto.orderType, savedOrder?.orderType) },
            { assertNotNull(savedOrderTable) },
            { assertEquals(testOrderTableDto.tableName, savedOrderTable?.name) },
            { assertNotNull(savedOrderMenu) }
        )
    }

    @Test
    @DisplayName("userId로 주문 정보 조회시 정상적으로 결과 조회가 되어야 한다.")
    @Transactional
    fun orderInfoSuccess1() {
        setUpOrders()

        val result = assertDoesNotThrow { orderTableService.orderInfo(1L) }

        assertAll(
            { assertNotNull(result) },
            { assertEquals("테이블1", result.tableName) },
            { assertEquals(TEST_NUM_OF_GUEST, result.numberOfGuests) },
            { assertEquals(TEST_USER_ID, result.userId) },
            { assertFalse(result.orderDtoList.isEmpty()) },
            { assertNotNull(result.orderDtoList[0]) },
            { assertNotNull(result.orderDtoList[0].orderMenuDtoList[0]) },
        )
    }

    private fun setUpOrders() {
        val testOrderTable = OrderTable(
            userId = TEST_USER_ID,
            name = "테이블1",
            emptyness = true,
            numberOfGuests = TEST_NUM_OF_GUEST
        )

        val testOrder = Order(
            orderType = "배달",
            paymentPrice = 0,
            paymentMethod = "카드결제",
            requirement = "?",
            deliveryAddress = "사랑시 고백구 행복동",
            orderTable = testOrderTable
        )

        val testOrderMenu = OrderMenu(
            quantity = 1L,
            menu = testMenu,
            order = testOrder,
        )

        orderTableRepository.save(testOrderTable)
        orderRepository.save(testOrder)
        orderMenuRepository.save(testOrderMenu)

        testOrderTable.orderList = mutableListOf(testOrder)
        orderTableRepository.save(testOrderTable)

        testOrder.orderMenuList = mutableListOf(testOrderMenu)
        orderRepository.save(testOrder)
    }
}
