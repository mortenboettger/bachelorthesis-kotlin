package io.mboettger.bachelorthesis.web.converter

import io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.create.CreateCustomerRequest
import io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.model.CustomerModel
import io.mboettger.bachelorthesis.web.model.CreateCustomerRequestV1
import io.mboettger.bachelorthesis.web.model.CustomerResponseV1

internal fun CustomerModel.toWebV1() = CustomerResponseV1(
    id = id,
    firstName = firstName,
    lastName = lastName,
    address = address.toWebV1(),
    phoneNumber = phoneNumber,
    emailAddress = emailAddress
)

internal fun CreateCustomerRequestV1.toBoundary() = CreateCustomerRequest(
    firstName = firstName,
    lastName = lastName,
    address = address.toBoundary(),
    phoneNumber = phoneNumber,
    emailAddress = emailAddress,
)
