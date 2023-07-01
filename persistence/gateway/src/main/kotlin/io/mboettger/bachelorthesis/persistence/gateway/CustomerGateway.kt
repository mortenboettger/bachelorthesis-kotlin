package io.mboettger.bachelorthesis.persistence.gateway

import io.mboettger.bachelorthesis.domain.customer.Customer
import io.mboettger.bachelorthesis.domain.customer.EmailAddress
import io.mboettger.bachelorthesis.domain.customer.address.Address
import java.util.stream.Stream

interface CustomerGateway : ReadWriteGateway<Customer> {
    fun findByEmail(emailAddress: EmailAddress): Customer?
    fun findByAddress(address: Address): Stream<Customer>
}