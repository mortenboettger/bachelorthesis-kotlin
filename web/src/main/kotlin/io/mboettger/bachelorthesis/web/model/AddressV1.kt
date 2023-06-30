package io.mboettger.bachelorthesis.web.model

import java.io.Serializable

data class AddressV1(
    val street: String,
    val houseNumber: String,
    val houseNumberAddition: String?,
    val postCode: String,
    val city: String,
    val district: String?,
) : Serializable
