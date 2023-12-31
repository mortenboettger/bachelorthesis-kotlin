package io.mboettger.bachelorthesis.usecase.impl.factory

import io.mboettger.bachelorthesis.persistence.gateway.GatewayFactory
import io.mboettger.bachelorthesis.usecase.boundary.InputBoundary
import io.mboettger.bachelorthesis.usecase.boundary.UseCaseFactory
import io.mboettger.bachelorthesis.usecase.boundary.UseCaseResponse
import io.mboettger.bachelorthesis.usecase.boundary.usecase.exception.UseCaseNotFoundException
import io.mboettger.bachelorthesis.usecase.impl.usecase.UseCases
import io.mboettger.bachelorthesis.usecase.impl.usecase.customer.customerUseCases
import kotlin.reflect.KClass

class UseCaseFactoryImpl(
    gatewayFactory: GatewayFactory,
    useCases: UseCases = customerUseCases(gatewayFactory)
) : UseCaseFactory {

    private val useCases = useCases.toMutableMap()

    override fun <R, P : UseCaseResponse<E>, Q : InputBoundary<R, P, E>, E : UseCaseResponse<E>> get(inputBoundary: KClass<out Q>): Q {
        return useCases[inputBoundary]?.let {
            @Suppress("UNCHECKED_CAST")
            it() as? Q
        }
            ?: throw UseCaseNotFoundException("Unable to find a useCase implementation by class ${inputBoundary.simpleName}")
    }
}