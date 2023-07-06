package io.mboettger.bachelorthesis.web.model

import java.io.Serializable

data class CreateCustomerRequestV1(
    val firstName: String,
    val lastName: String,
    val address: AddressV1,
    val phoneNumber: String? = null,
    val emailAddress: String? = null,
) : Serializable
