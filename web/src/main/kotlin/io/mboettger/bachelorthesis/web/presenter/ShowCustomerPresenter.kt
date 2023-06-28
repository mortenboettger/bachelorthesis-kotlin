package io.mboettger.bachelorthesis.web.presenter

import io.mboettger.bachelorthesis.usecase.boundary.useCase.customer.show.ShowCustomerResponse
import io.mboettger.bachelorthesis.web.converter.toWebV1
import io.mboettger.bachelorthesis.web.model.CustomerResponseV1
import org.springframework.http.HttpStatus
import org.springframework.web.client.HttpServerErrorException

internal class ShowCustomerPresenter : RestPresenter<CustomerResponseV1, ShowCustomerResponse, ShowCustomerResponse.Success, ShowCustomerResponse.Error>() {
    private lateinit var model : CustomerResponseV1

    override fun webResponse() = model

    override fun success(response: ShowCustomerResponse.Success) {
        model = response.customer.toWebV1()
    }

    override fun failure(response: ShowCustomerResponse.Error) {
        when (response) { // TODO
            is ShowCustomerResponse.Error.NotFound -> throw HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, response.message)
            is ShowCustomerResponse.Error.RequestValidationFailed -> throw HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, response.message)
            is ShowCustomerResponse.Error.Unknown -> throw HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, response.message)
        }
    }
}