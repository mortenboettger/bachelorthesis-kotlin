package io.mboettger.bachelorthesis.domain.customer

import io.mboettger.bachelorthesis.domain.DomainModel
import io.mboettger.bachelorthesis.domain.customer.address.Address

data class Customer(
    override val id: String,
    val firstName: FirstName,
    val lastName: LastName,
    val address: Address,
    val phoneNumber: PhoneNumber? = null,
    val emailAddress: EmailAddress? = null,
): DomainModel(id)
