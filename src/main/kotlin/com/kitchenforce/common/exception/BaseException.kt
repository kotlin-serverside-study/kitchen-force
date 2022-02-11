package com.kitchenforce.common.exception

import org.springframework.http.HttpStatus

open class BaseException(
    open val errorStatus: HttpStatus,
    open val errorCode: Int,
    open val errorMessage: String,
    open val description: String? = null,
    override val cause: Throwable? = null
) : RuntimeException(
    """
               [ErrorCode] : $errorCode
               [ErrorMessage] : $errorMessage
               [Description] : $description               
    """.trimIndent(),
    cause
)
