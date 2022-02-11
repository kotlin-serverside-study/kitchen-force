package com.kitchenforce.common.exception

import org.springframework.http.HttpStatus

interface ErrorCode {
    val errorStatus: HttpStatus
    val errorMessage: String
}
