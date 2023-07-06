package io.mboettger.bachelorthesis.usecase.impl.converter

import io.mboettger.bachelorthesis.domain.customer.address.*
import io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.model.AddressModel

internal fun AddressModel.toDomain() = Address(
    street = Street(street),
    houseNumber = HouseNumber(houseNumber),
    houseNumberAddition = houseNumberAddition?.let { HouseNumberAddition(it) },
    postCode = PostCode(postCode),
    city = City(city),
    district = district?.let { District(it) },
)

internal fun Address.toBoundary() = AddressModel(
    street = street.value,
    houseNumber = houseNumber.value,
    houseNumberAddition = houseNumberAddition?.value,
    postCode = postCode.value,
    city = city.value,
    district = district?.value,
)