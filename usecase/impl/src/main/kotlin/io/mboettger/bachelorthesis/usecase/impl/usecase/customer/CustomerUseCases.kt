package io.mboettger.bachelorthesis.usecase.impl.usecase.customer

import io.mboettger.bachelorthesis.persistence.gateway.invoke
import io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.create.CreateCustomerUseCase
import io.mboettger.bachelorthesis.usecase.boundary.usecase.customer.show.ShowCustomerUseCase
import io.mboettger.bachelorthesis.usecase.impl.usecase.UseCasesDefinition

internal val customerUseCases: UseCasesDefinition = { d ->
    mapOf(
        ShowCustomerUseCase::class to { ShowCustomerUseCaseImpl(d()) },
        CreateCustomerUseCase::class to { CreateCustomerUseCaseImpl(d()) },
    )
}