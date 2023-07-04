package io.mboettger.bachelorthesis.usecase.boundary.useCase.customer.show

import io.mboettger.bachelorthesis.usecase.boundary.UseCaseResponse
import io.mboettger.bachelorthesis.usecase.boundary.useCase.UseCaseErrorMessageContainer
import io.mboettger.bachelorthesis.usecase.boundary.useCase.UseCaseUnknownErrorContainer
import io.mboettger.bachelorthesis.usecase.boundary.useCase.UseCaseValidationErrorContainer
import io.mboettger.bachelorthesis.usecase.boundary.useCase._helper.ValidationErrors
import io.mboettger.bachelorthesis.usecase.boundary.useCase.customer.model.CustomerModel

sealed class ShowCustomerResponse(
    override val success: Boolean = false
) : UseCaseResponse<ShowCustomerResponse.Error> {
    data class Success(
        val customer: CustomerModel
    ) : ShowCustomerResponse(success = true)

    sealed class Error(
        override val message: String
    ) : ShowCustomerResponse(success = false), UseCaseErrorMessageContainer {
        class NotFound(
            val identifier: String,
        ) : Error("Unable to find a customer by identifier $identifier")

        class RequestValidationFailed(
            override val validationErrors: ValidationErrors,
        ) : Error("Invalid request to show a customer"),
            UseCaseValidationErrorContainer

        class Unknown(
            override val error: Throwable,
        ) : Error("Error occurred while trying to find a customer"),
            UseCaseUnknownErrorContainer
    }
}