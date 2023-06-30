package io.mboettger.bachelorthesis.web.model

import java.io.Serializable

data class CreateCustomerRequestV1(
    val name: String,
    val surName: String,
    val address: AddressV1,
    val phoneNumber: String?,
    val emailAddress: String?,
) : Serializable
