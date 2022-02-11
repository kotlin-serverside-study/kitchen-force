package com.kitchenforce.domain.delivery

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DeliveryAddressRepository : JpaRepository<DeliveryAddress, Int>
