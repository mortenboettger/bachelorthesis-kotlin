package io.mboettger.bachelorthesis.usecase.impl.useCase.customer

import io.mboettger.bachelorthesis.domain.customer.EmailAddress
import io.mboettger.bachelorthesis.persistence.gateway.CustomerGateway
import io.mboettger.bachelorthesis.usecase.boundary.useCase.customer.show.ShowCustomerRequest
import io.mboettger.bachelorthesis.usecase.boundary.useCase.customer.show.ShowCustomerResponse
import io.mboettger.bachelorthesis.usecase.boundary.useCase.customer.show.ShowCustomerUseCase
import io.mboettger.bachelorthesis.usecase.impl.converter.toBoundary
import io.mboettger.bachelorthesis.usecase.impl.useCase._helper.useCase

class ShowCustomerUseCaseImpl(
    private val customerGateway: CustomerGateway
) : ShowCustomerUseCase by useCase({
    when (request) {
        is ShowCustomerRequest.ById -> {
            customerGateway.findOneOrNull(request.customerId)?.let {
                ShowCustomerResponse.Success(it.toBoundary())
            } ?: ShowCustomerResponse.Error.NotFound(request.customerId)
        }

        is ShowCustomerRequest.ByEmailAddress -> {
            customerGateway.findByEmail(EmailAddress(request.emailAddress))?.let {
                ShowCustomerResponse.Success(it.toBoundary())
            } ?: ShowCustomerResponse.Error.NotFound(request.emailAddress)
        }
    }
})