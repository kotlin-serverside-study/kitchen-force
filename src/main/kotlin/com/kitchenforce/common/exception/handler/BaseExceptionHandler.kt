package com.kitchenforce.common.exception.handler

import com.kitchenforce.common.exception.BaseErrorCodeType
import com.kitchenforce.common.exception.BaseErrorResponse
import com.kitchenforce.common.exception.BaseException
import com.kitchenforce.domain.products.exception.ProductException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import java.util.stream.Collectors
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class BaseExceptionHandler {
    @ExceptionHandler(
        BaseException::class,
        ProductException::class
    )
    @ResponseBody
    fun handleCommonException(
        e: BaseException,
        request: HttpServletRequest?
    ): ResponseEntity<BaseErrorResponse?> {
        return ResponseEntity(BaseErrorResponse.createErrorResponse(e), e.errorStatus)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(
        e: MethodArgumentNotValidException,
        request: HttpServletRequest?
    ): ResponseEntity<BaseErrorResponse?> {
        val fieldErrorList = e.bindingResult.fieldErrors
        val returnList = fieldErrorList.stream()
            .map {
                "([Field] : " +
                    it.field +
                    "  [Value] : " +
                    it.rejectedValue +
                    "  [Message] : " +
                    it.defaultMessage +
                    ")"
            }
            .collect(Collectors.toList())
        val boxedException = BaseException(
            errorStatus = HttpStatus.BAD_REQUEST,
            errorMessage = returnList.toString(),
            cause = e,
            description = null,
            errorCode = HttpStatus.BAD_REQUEST.value(),
        )
        return ResponseEntity(
            BaseErrorResponse.createErrorResponse(boxedException),
            boxedException.errorStatus
        )
    }

    @ExceptionHandler(Exception::class)
    fun handleException(
        e: Exception?,
        request: HttpServletRequest
    ): ResponseEntity<BaseErrorResponse?> {
        request.session
        val boxedException = BaseException(
            errorStatus = BaseErrorCodeType.UNKNOWN_ERROR.errorStatus,
            errorMessage = BaseErrorCodeType.UNKNOWN_ERROR.errorMessage,
            cause = e,
            description = null,
            errorCode = BaseErrorCodeType.UNKNOWN_ERROR.errorStatus.value()
        )

        return ResponseEntity(
            BaseErrorResponse.createErrorResponse(boxedException),
            boxedException.errorStatus
        )
    }
}
