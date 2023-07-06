package io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.create

import io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.model.AddressModel

data class CreateCustomerRequest(
    val firstName: String,
    val lastName: String,
    val address: AddressModel,
    val phoneNumber: String? = null,
    val emailAddress: String? = null,
)
