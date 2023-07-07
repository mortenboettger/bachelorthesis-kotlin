package io.mboettger.bachelorthesis.usecase.impl.usecase.customer

import io.mboettger.bachelorthesis.domain.customer.EmailAddress
import io.mboettger.bachelorthesis.persistence.gateway.CustomerGateway
import io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.show.ShowCustomerRequest
import io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.show.ShowCustomerResponse
import io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.show.ShowCustomerUseCase
import io.mboettger.bachelorthesis.usecase.impl.converter.toBoundary
import io.mboettger.bachelorthesis.usecase.impl.usecase.helper.error.UseCaseValidationError
import io.mboettger.bachelorthesis.usecase.impl.usecase.helper.useCase
import io.mboettger.bachelorthesis.usecase.impl.usecase.helper.validation.beNull
import io.mboettger.bachelorthesis.usecase.impl.usecase.helper.validation.shouldNot

class ShowCustomerUseCaseImpl(
    private val customerGateway: CustomerGateway
) : ShowCustomerUseCase by useCase({

    when (request) {
        is ShowCustomerRequest.ById -> {
            try {
                check(request.customerId shouldNot beNull) { "Customer ID can not be empty" }
            } catch (e: IllegalStateException) {
                throw UseCaseValidationError(e)
            }

            customerGateway.findOneOrNull(request.customerId)?.let {
                ShowCustomerResponse.Success(it.toBoundary())
            } ?: ShowCustomerResponse.Error.NotFound(request.customerId)
        }

        is ShowCustomerRequest.ByEmailAddress -> {
            val emailAddress = try {
                check(request.emailAddress shouldNot beNull) { "E-Mail address can not be empty" }
                EmailAddress(request.emailAddress)
            } catch (e: IllegalStateException) {
                throw UseCaseValidationError(e)
            }

            customerGateway.findByEmail(emailAddress)?.let {
                ShowCustomerResponse.Success(it.toBoundary())
            } ?: ShowCustomerResponse.Error.NotFound(request.emailAddress)
        }
    }
})