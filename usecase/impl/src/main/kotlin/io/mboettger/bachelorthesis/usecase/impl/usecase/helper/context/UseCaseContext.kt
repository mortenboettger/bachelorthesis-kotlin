package io.mboettger.bachelorthesis.usecase.impl.usecase.helper.context

internal data class UseCaseContext<R : Any>(
    val request: R,
)