package io.mboettger.bachelorthesis.web.presenter

import io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.create.CreateCustomerResponse
import io.mboettger.bachelorthesis.web.helper.asInternalServerError
import io.mboettger.bachelorthesis.web.helper.asValidationError
import io.mboettger.bachelorthesis.web.model.CreateCustomerResponseV1

internal class CreateCustomerPresenter : RestPresenter<CreateCustomerResponseV1, CreateCustomerResponse, CreateCustomerResponse.Success, CreateCustomerResponse.Error>() {

    private lateinit var model: CreateCustomerResponseV1

    override fun webResponse() = model

    override fun success(response: CreateCustomerResponse.Success) {
        model = CreateCustomerResponseV1(response.id)
    }

    override fun failure(response: CreateCustomerResponse.Error) {
        when (response) {
            is CreateCustomerResponse.Error.RequestValidationFailed -> response.asValidationError()
            is CreateCustomerResponse.Error.Unknown -> response.asInternalServerError()
        }
    }
}