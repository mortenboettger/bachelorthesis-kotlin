package io.mboettger.bachelorthesis.usecase.impl.usecase.helper.error

import io.mboettger.bachelorthesis.usecase.boundary.UseCaseResponse

internal class UseCaseResponseError(val response: UseCaseResponse<*>) : Throwable()