package io.mboettger.bachelorthesis.usecase.boundary.useCase.customer.model

data class CustomerModel(
    val id: String,
    val name: String,
    val surName: String,
    val address: AddressModel,
    val phoneNumber: String?,
    val emailAddress: String?,
)
