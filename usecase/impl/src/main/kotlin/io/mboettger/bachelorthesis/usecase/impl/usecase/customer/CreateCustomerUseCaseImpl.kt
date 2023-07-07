package io.mboettger.bachelorthesis.usecase.impl.usecase.customer

import io.mboettger.bachelorthesis.domain.customer.*
import io.mboettger.bachelorthesis.persistence.gateway.CustomerGateway
import io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.create.CreateCustomerResponse
import io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.create.CreateCustomerUseCase
import io.mboettger.bachelorthesis.usecase.impl.converter.toDomain
import io.mboettger.bachelorthesis.usecase.impl.usecase.helper.error.UseCaseValidationError
import io.mboettger.bachelorthesis.usecase.impl.usecase.helper.useCase
import io.mboettger.bachelorthesis.usecase.impl.usecase.helper.validation.*
import io.mboettger.bachelorthesis.usecase.impl.usecase.helper.validation.shouldNot

internal class CreateCustomerUseCaseImpl(
    private val customerGateway: CustomerGateway
) : CreateCustomerUseCase by useCase(
    {
        check(request.emailAddress?.let { EmailAddress(it) } shouldNot customerGateway::existsByEmail) { "${request.emailAddress} already in use" }
        check(request.phoneNumber shouldBe null || request.phoneNumber shouldMatch Regex("^\\+?[1-9][0-9\\-]+\$")) { "Malformed phone-number" }
    },
    {
        val customer = Customer(
            id = "",
            firstName = FirstName(request.firstName),
            lastName = LastName(request.lastName),
            address = request.address.toDomain(),
            phoneNumber = request.phoneNumber?.let { PhoneNumber(it.replace("-", "")) },
            emailAddress = request.emailAddress?.let { EmailAddress(it) },
        )

        CreateCustomerResponse.Success(
            customerGateway.save(customer).id
        )
    }
)