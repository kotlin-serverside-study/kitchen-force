package com.kitchenforce.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/delivery")
class DeliveryController {

    @GetMapping("/get")
    fun hello(): String {
        return "hello get mapping :D"
    }
}
