package io.mboettger.bachelorthesis.usecase.impl.usecase

import io.mboettger.bachelorthesis.persistence.gateway.GatewayFactory

internal typealias UseCasesDefinition = (GatewayFactory) -> UseCases
