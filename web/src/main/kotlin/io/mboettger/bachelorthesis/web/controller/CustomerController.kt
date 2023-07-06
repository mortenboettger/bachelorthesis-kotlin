package io.mboettger.bachelorthesis.web.controller

import io.mboettger.bachelorthesis.usecase.boundary.UseCaseFactory
import io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.create.CreateCustomerUseCase
import io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.show.ShowCustomerRequest
import io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.show.ShowCustomerUseCase
import io.mboettger.bachelorthesis.web.converter.toBoundary
import io.mboettger.bachelorthesis.web.helper.execute
import io.mboettger.bachelorthesis.web.model.CreateCustomerRequestV1
import io.mboettger.bachelorthesis.web.model.CreateCustomerResponseV1
import io.mboettger.bachelorthesis.web.model.CustomerResponseV1
import io.mboettger.bachelorthesis.web.presenter.CreateCustomerPresenter
import io.mboettger.bachelorthesis.web.presenter.ShowCustomerPresenter
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/customer")
class CustomerController(
    private val useCaseFactory: UseCaseFactory
) {

    @GetMapping("{id}")
    fun getById(@PathVariable id: String): CustomerResponseV1 =
        useCaseFactory.execute(ShowCustomerUseCase::class, ShowCustomerPresenter()) {
            ShowCustomerRequest.ById(id)
        }

    @GetMapping("/by-email/{email}")
    fun getByEmail(@PathVariable email: String): CustomerResponseV1 =
        useCaseFactory.execute(ShowCustomerUseCase::class, ShowCustomerPresenter()) {
            ShowCustomerRequest.ByEmailAddress(email)
        }

    @PostMapping
    fun create(@RequestBody request: CreateCustomerRequestV1): CreateCustomerResponseV1 =
        useCaseFactory.execute(CreateCustomerUseCase::class, CreateCustomerPresenter()) {
            request.toBoundary()
        }
}