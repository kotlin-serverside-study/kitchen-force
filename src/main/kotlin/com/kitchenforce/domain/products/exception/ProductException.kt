package com.kitchenforce.domain.products.exception

import com.kitchenforce.common.exception.BaseException

class ProductException(
    productErrorCodeType: ProductErrorCodeType,
    override val description: String? = null,
    override val cause: Throwable? = null
) : BaseException(productErrorCodeType.errorStatus, productErrorCodeType.errorStatus.value(), productErrorCodeType.errorMessage, description, cause)
