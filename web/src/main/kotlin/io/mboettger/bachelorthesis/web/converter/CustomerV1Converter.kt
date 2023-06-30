package io.mboettger.bachelorthesis.web.converter

import io.mboettger.bachelorthesis.usecase.boundary.useCase.customer.create.CreateCustomerRequest
import io.mboettger.bachelorthesis.usecase.boundary.useCase.customer.model.CustomerModel
import io.mboettger.bachelorthesis.web.model.CreateCustomerRequestV1
import io.mboettger.bachelorthesis.web.model.CustomerResponseV1

internal fun CustomerModel.toWebV1() = CustomerResponseV1(
    id = id,
    name = name,
    surName = surName,
    address = address.toWebV1(),
    phoneNumber = phoneNumber,
    emailAddress = emailAddress
)

internal fun CreateCustomerRequestV1.toBoundary() = CreateCustomerRequest(
    name = name,
    surName = surName,
    address = address.toBoundary(),
    phoneNumber = phoneNumber,
    emailAddress = emailAddress,
)
