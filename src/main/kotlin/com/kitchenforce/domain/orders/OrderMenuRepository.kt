package com.kitchenforce.domain.orders

import org.springframework.data.jpa.repository.JpaRepository

interface OrderMenuRepository : JpaRepository<OrderMenu, Long>
