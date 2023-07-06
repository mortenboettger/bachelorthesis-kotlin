package io.mboettger.bachelorthesis.web.model

import java.io.Serializable

data class CustomerResponseV1(
    val id: String,
    val firstName: String,
    val lastName: String,
    val address: AddressV1,
    val phoneNumber: String? = null,
    val emailAddress: String? = null,
) : Serializable
