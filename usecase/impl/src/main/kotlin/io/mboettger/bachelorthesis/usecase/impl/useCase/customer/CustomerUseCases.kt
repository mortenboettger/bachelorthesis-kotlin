package io.mboettger.bachelorthesis.usecase.impl.useCase.customer

import io.mboettger.bachelorthesis.persistence.gateway.invoke
import io.mboettger.bachelorthesis.usecase.boundary.useCase.customer.create.CreateCustomerUseCase
import io.mboettger.bachelorthesis.usecase.boundary.useCase.customer.show.ShowCustomerUseCase
import io.mboettger.bachelorthesis.usecase.impl.useCase.UseCasesDefinition

internal val customerUseCases: UseCasesDefinition = { d ->
    mapOf(
        ShowCustomerUseCase::class to { ShowCustomerUseCaseImpl(d()) },
        CreateCustomerUseCase::class to { CreateCustomerUseCaseImpl(d()) },
    )
}