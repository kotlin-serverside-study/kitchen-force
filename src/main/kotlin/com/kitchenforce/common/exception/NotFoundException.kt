package com.kitchenforce.common.exception

import org.springframework.http.HttpStatus

class NotFoundException(
    override val errorMessage: String,
    override val description: String? = null,
    override val cause: Throwable? = null
) : BaseException(
    errorStatus = HttpStatus.NOT_FOUND,
    errorCode = HttpStatus.NOT_FOUND.value(),
    errorMessage = errorMessage,
    description = description,
    cause = cause,
)