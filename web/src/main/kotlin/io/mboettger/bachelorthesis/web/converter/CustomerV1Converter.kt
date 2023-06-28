package io.mboettger.bachelorthesis.web.converter

import io.mboettger.bachelorthesis.usecase.boundary.useCase.customer.model.CustomerModel
import io.mboettger.bachelorthesis.web.model.CustomerResponseV1

internal fun CustomerModel.toWebV1() = CustomerResponseV1(
    id = id
)