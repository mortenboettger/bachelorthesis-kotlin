package io.mboettger.bachelorthesis.usecase.impl.useCase._helper.error

import io.mboettger.bachelorthesis.usecase.boundary.UseCaseResponse

internal class UseCaseResponseError(val response: UseCaseResponse<*>) : Throwable()