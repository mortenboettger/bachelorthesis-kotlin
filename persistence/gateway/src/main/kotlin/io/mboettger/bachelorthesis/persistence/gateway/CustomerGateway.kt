package io.mboettger.bachelorthesis.persistence.gateway

import io.mboettger.bachelorthesis.domain.customer.Customer
import io.mboettger.bachelorthesis.domain.customer.EmailAddress

interface CustomerGateway : ReadWriteGateway<Customer> {
    fun findByEmail(emailAddress: EmailAddress): Customer?

    fun existsByEmail(emailAddress: EmailAddress): Boolean
}