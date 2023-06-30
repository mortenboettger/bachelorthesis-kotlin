package io.mboettger.bachelorthesis.persistence.gateway

import io.mboettger.bachelorthesis.domain.customer.Customer
import io.mboettger.bachelorthesis.domain.customer.EmailAddress
import io.mboettger.bachelorthesis.domain.customer.address.Address
import java.util.stream.Stream

interface CustomerGateway : ReadWriteGateway<Customer> {
    fun findCustomerByEmail(emailAddress: EmailAddress): Customer?
    fun findCustomersByAddress(address: Address): Stream<Customer>
}