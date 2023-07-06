package io.mboettger.bachelorthesis.web.model

import java.io.Serializable

data class AddressV1(
    val street: String,
    val houseNumber: String,
    val houseNumberAddition: String? = null,
    val postCode: String,
    val city: String,
    val district: String? = null,
) : Serializable
