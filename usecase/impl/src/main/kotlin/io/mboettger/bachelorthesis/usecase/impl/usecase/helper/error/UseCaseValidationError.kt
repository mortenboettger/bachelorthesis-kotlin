package io.mboettger.bachelorthesis.usecase.impl.usecase.helper.error

internal class UseCaseValidationError(throwable: Throwable): Throwable("An error happened during validation", throwable)