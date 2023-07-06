package io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.model

data class CustomerModel(
    val id: String,
    val firstName: String,
    val lastName: String,
    val address: AddressModel,
    val phoneNumber: String? = null,
    val emailAddress: String? = null,
)
