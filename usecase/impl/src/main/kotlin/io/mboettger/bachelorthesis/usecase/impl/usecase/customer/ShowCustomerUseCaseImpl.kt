package io.mboettger.bachelorthesis.usecase.impl.usecase.customer

import io.mboettger.bachelorthesis.domain.customer.EmailAddress
import io.mboettger.bachelorthesis.persistence.gateway.CustomerGateway
import io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.show.ShowCustomerRequest
import io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.show.ShowCustomerResponse
import io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.show.ShowCustomerUseCase
import io.mboettger.bachelorthesis.usecase.impl.converter.toBoundary
import io.mboettger.bachelorthesis.usecase.impl.usecase.helper.useCase
import io.mboettger.bachelorthesis.usecase.impl.usecase.helper.validation.shouldNotBe

class ShowCustomerUseCaseImpl(
    private val customerGateway: CustomerGateway
) : ShowCustomerUseCase by useCase(
    {
        when (request) {
            is ShowCustomerRequest.ById -> check(request.customerId shouldNotBe null) { "Customer ID can not be empty" }
            is ShowCustomerRequest.ByEmailAddress -> check(request.emailAddress shouldNotBe null) { "E-Mail address can not be empty" }
        }
    },
    {

        when (request) {
            is ShowCustomerRequest.ById ->
                customerGateway.findOneOrNull(request.customerId)?.let {
                    ShowCustomerResponse.Success(it.toBoundary())
                } ?: ShowCustomerResponse.Error.NotFound(request.customerId)

            is ShowCustomerRequest.ByEmailAddress ->
                customerGateway.findByEmail(EmailAddress(request.emailAddress))?.let {
                    ShowCustomerResponse.Success(it.toBoundary())
                } ?: ShowCustomerResponse.Error.NotFound(request.emailAddress)
        }
    }
)