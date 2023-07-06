package io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.model

data class AddressModel(
    val street: String,
    val houseNumber: String,
    val houseNumberAddition: String? = null,
    val postCode: String,
    val city: String,
    val district: String? = null,
)
