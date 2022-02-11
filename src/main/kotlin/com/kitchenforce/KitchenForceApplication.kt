package com.kitchenforce

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class KitchenForceApplication

fun main(args: Array<String>) {
    runApplication<KitchenForceApplication>(*args)
}
