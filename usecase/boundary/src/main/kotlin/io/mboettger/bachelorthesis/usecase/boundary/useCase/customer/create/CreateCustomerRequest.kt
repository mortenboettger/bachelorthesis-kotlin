package io.mboettger.bachelorthesis.usecase.boundary.useCase.customer.create

import io.mboettger.bachelorthesis.usecase.boundary.useCase.customer.model.AddressModel

data class CreateCustomerRequest(
    val name: String,
    val surName: String,
    val address: AddressModel,
    val phoneNumber: String?,
    val emailAddress: String?,
)
