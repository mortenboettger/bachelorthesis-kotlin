package io.mboettger.bachelorthesis.usecase.impl.usecase.customer

import io.mboettger.bachelorthesis.domain.customer.*
import io.mboettger.bachelorthesis.persistence.gateway.CustomerGateway
import io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.create.CreateCustomerResponse
import io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.create.CreateCustomerUseCase
import io.mboettger.bachelorthesis.usecase.impl.converter.toDomain
import io.mboettger.bachelorthesis.usecase.impl.usecase.helper.error.UseCaseValidationError
import io.mboettger.bachelorthesis.usecase.impl.usecase.helper.useCase
import io.mboettger.bachelorthesis.usecase.impl.usecase.helper.validation.beNull
import io.mboettger.bachelorthesis.usecase.impl.usecase.helper.validation.should
import io.mboettger.bachelorthesis.usecase.impl.usecase.helper.validation.shouldMatch
import io.mboettger.bachelorthesis.usecase.impl.usecase.helper.validation.shouldNot

internal class CreateCustomerUseCaseImpl(
    private val customerGateway: CustomerGateway
) : CreateCustomerUseCase by useCase({

    val customer = try {
        check(request.emailAddress?.let { EmailAddress(it) } shouldNot customerGateway::existsByEmail) { "${request.emailAddress} already in use" }
        check(request.phoneNumber should beNull || request.phoneNumber shouldMatch Regex("^\\+?[1-9][0-9\\-]+\$")) { "Malformed phone-number" }

        Customer(
            id = "",
            firstName = FirstName(request.firstName),
            lastName = LastName(request.lastName),
            address = request.address.toDomain(),
            phoneNumber = request.phoneNumber?.let { PhoneNumber(it.replace("-", "")) },
            emailAddress = request.emailAddress?.let { EmailAddress(it) },
        )
    } catch (e: IllegalStateException) {
        throw UseCaseValidationError(e)
    }

    CreateCustomerResponse.Success(
        customerGateway.save(customer).id
    )
})