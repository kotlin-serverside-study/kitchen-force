package com.kitchenforce.common.exception

class BaseErrorResponse(
    val errorCode: Int,
    val errorMessage: String? = null
) {

    companion object {
        fun createErrorResponse(baseException: BaseException): BaseErrorResponse {
            return BaseErrorResponse(
                baseException.errorCode,
                baseException.errorMessage
            )
        }
    }
}
