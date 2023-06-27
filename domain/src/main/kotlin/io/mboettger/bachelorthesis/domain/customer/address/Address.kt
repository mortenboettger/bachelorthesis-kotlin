package io.mboettger.bachelorthesis.domain.customer.address

data class Address(
    val street: Street,
    val houseNumber: HouseNumber,
    val houseNumberAddition: HouseNumberAddition? = null,
    val postCode: PostCode,
    val city: City,
    val district: District? = null,
)
