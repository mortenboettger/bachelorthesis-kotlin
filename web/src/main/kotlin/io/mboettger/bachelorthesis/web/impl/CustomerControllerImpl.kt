package io.mboettger.bachelorthesis.web.impl

import io.mboettger.bachelorthesis.usecase.boundary.UseCaseFactory
import io.mboettger.bachelorthesis.usecase.boundary.useCase.customer.show.ShowCustomerRequest
import io.mboettger.bachelorthesis.usecase.boundary.useCase.customer.show.ShowCustomerUseCase
import io.mboettger.bachelorthesis.web.CustomerController
import io.mboettger.bachelorthesis.web._helper.execute
import io.mboettger.bachelorthesis.web.presenter.ShowCustomerPresenter
import org.springframework.stereotype.Component

@Component
class CustomerControllerImpl(
    private val useCaseFactory: UseCaseFactory
) : CustomerController {
    override fun getById(id: String) = useCaseFactory.execute(ShowCustomerUseCase::class, ShowCustomerPresenter()) {
        ShowCustomerRequest.ById(id)
    }
}