package io.mboettger.bachelorthesis.web.converter

import io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.model.AddressModel
import io.mboettger.bachelorthesis.web.model.AddressV1

internal fun AddressModel.toWebV1() = AddressV1(
    street = street,
    houseNumber = houseNumber,
    houseNumberAddition = houseNumberAddition,
    postCode = postCode,
    city = city,
    district = district,
)

internal fun AddressV1.toBoundary() = AddressModel(
    street = street,
    houseNumber = houseNumber,
    houseNumberAddition = houseNumberAddition,
    postCode = postCode,
    city = city,
    district = district,
)