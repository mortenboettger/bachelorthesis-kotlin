package io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.create

import io.mboettger.bachelorthesis.usecase.boundary.UseCaseResponse
import io.mboettger.bachelorthesis.usecase.boundary.usecase.UseCaseErrorMessageContainer
import io.mboettger.bachelorthesis.usecase.boundary.usecase.UseCaseUnknownErrorContainer
import io.mboettger.bachelorthesis.usecase.boundary.usecase.UseCaseValidationErrorContainer

sealed class CreateCustomerResponse(
    override val success: Boolean = false
) : UseCaseResponse<CreateCustomerResponse.Error> {
    data class Success(
        val id: String,
    ) : CreateCustomerResponse(success = true)

    sealed class Error(
        override val message: String
    ) : CreateCustomerResponse(success = false), UseCaseErrorMessageContainer {
        class RequestValidationFailed(
            override val validationError: String,
        ) : Error("Invalid request to create a customer"),
            UseCaseValidationErrorContainer

        class Unknown(
            override val error: Throwable,
        ) : Error("Error occurred while trying to create a customer"),
            UseCaseUnknownErrorContainer
    }
}