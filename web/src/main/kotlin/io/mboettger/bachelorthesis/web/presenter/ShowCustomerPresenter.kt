package io.mboettger.bachelorthesis.web.presenter

import io.mboettger.bachelorthesis.usecase.boundary.useCase.customer.show.ShowCustomerResponse
import io.mboettger.bachelorthesis.web._helper.asInternalServerError
import io.mboettger.bachelorthesis.web._helper.asValidationError
import io.mboettger.bachelorthesis.web._helper.throwWithStatus
import io.mboettger.bachelorthesis.web.converter.toWebV1
import io.mboettger.bachelorthesis.web.model.CustomerResponseV1
import org.springframework.http.HttpStatus

internal class ShowCustomerPresenter : RestPresenter<CustomerResponseV1, ShowCustomerResponse, ShowCustomerResponse.Success, ShowCustomerResponse.Error>() {
    private lateinit var model : CustomerResponseV1

    override fun webResponse() = model

    override fun success(response: ShowCustomerResponse.Success) {
        model = response.customer.toWebV1()
    }

    override fun failure(response: ShowCustomerResponse.Error) {
        when (response) {
            is ShowCustomerResponse.Error.NotFound -> response.throwWithStatus(HttpStatus.NOT_FOUND)
            is ShowCustomerResponse.Error.RequestValidationFailed -> response.asValidationError()
            is ShowCustomerResponse.Error.Unknown -> response.asInternalServerError()
        }
    }
}