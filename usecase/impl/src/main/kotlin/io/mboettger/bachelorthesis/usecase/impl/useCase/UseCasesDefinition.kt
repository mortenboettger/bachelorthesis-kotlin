package io.mboettger.bachelorthesis.usecase.impl.useCase

import io.mboettger.bachelorthesis.persistence.gateway.GatewayFactory

internal typealias UseCasesDefinition = (GatewayFactory) -> UseCases
