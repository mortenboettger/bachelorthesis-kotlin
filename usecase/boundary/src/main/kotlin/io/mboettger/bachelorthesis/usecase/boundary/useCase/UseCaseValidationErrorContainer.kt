package io.mboettger.bachelorthesis.usecase.boundary.useCase

import io.mboettger.bachelorthesis.usecase.boundary.useCase._helper.ValidationErrors

interface UseCaseValidationErrorContainer {
    val validationErrors: ValidationErrors
}