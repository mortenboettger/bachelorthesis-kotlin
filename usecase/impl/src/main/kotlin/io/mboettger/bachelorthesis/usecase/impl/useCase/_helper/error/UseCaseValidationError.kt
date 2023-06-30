package io.mboettger.bachelorthesis.usecase.impl.useCase._helper.error

import io.mboettger.bachelorthesis.usecase.boundary.useCase._helper.ValidationErrors

internal class UseCaseValidationError(
    val messages: ValidationErrors
): Throwable(messages.toString())