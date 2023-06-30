package io.mboettger.bachelorthesis.usecase.impl.converter

import io.mboettger.bachelorthesis.domain.customer.*
import io.mboettger.bachelorthesis.usecase.boundary.useCase.customer.model.CustomerModel

internal fun CustomerModel.toDomain() = Customer(
    id = id,
    name = Name(name),
    surName = SurName(surName),
    address = address.toDomain(),
    phoneNumber = phoneNumber?.let { PhoneNumber(it) },
    emailAddress = emailAddress?.let { EmailAddress(it) },
)

internal fun Customer.toBoundary() = CustomerModel(
    id = id,
    name = name.value,
    surName = surName.value,
    address = address.toBoundary(),
    phoneNumber = phoneNumber?.value,
    emailAddress = emailAddress?.value,
)