package io.mboettger.bachelorthesis.usecase.impl.converter

import io.mboettger.bachelorthesis.domain.customer.*
import io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.model.CustomerModel

internal fun CustomerModel.toDomain() = Customer(
    id = id,
    firstName = FirstName(firstName),
    lastName = LastName(lastName),
    address = address.toDomain(),
    phoneNumber = phoneNumber?.let { PhoneNumber(it) },
    emailAddress = emailAddress?.let { EmailAddress(it) },
)

internal fun Customer.toBoundary() = CustomerModel(
    id = id,
    firstName = firstName.value,
    lastName = lastName.value,
    address = address.toBoundary(),
    phoneNumber = phoneNumber?.value,
    emailAddress = emailAddress?.value,
)