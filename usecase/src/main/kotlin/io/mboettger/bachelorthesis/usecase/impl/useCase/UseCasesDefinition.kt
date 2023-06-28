package io.mboettger.bachelorthesis.usecase.impl.useCase

import io.mboettger.bachelorthesis.persistence.boundary.GatewayFactory

internal typealias UseCasesDefiniton = (GatewayFactory) -> UseCases
