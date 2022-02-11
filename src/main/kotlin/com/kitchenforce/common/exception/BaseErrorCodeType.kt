package com.kitchenforce.common.exception

import org.springframework.http.HttpStatus

enum class BaseErrorCodeType(override val errorStatus: HttpStatus, override val errorMessage: String) : ErrorCode {
    UNKNOWN_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 내부 서버 에러 입니다.")
    ;
}
