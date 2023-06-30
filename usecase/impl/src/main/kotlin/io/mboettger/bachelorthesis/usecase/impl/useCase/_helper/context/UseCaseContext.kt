package io.mboettger.bachelorthesis.usecase.impl.useCase._helper.context

import io.mboettger.bachelorthesis.usecase.boundary.UseCaseResponse
import io.mboettger.bachelorthesis.usecase.impl.useCase._helper.error.UseCaseErrorHelper
import io.mboettger.bachelorthesis.usecase.impl.useCase._helper.error.UseCaseResponseError
import io.mboettger.bachelorthesis.usecase.impl.useCase._helper.error.UseCaseValidationError
import io.mboettger.bachelorthesis.usecase.impl.useCase._helper.validation.UseCaseValidationContext
import kotlin.reflect.KClass

internal class UseCaseContext<R : Any, E : UseCaseResponse<E>>(
    val request: R,
    private val useCaseErrorResponseClass: KClass<out E>
) {
    fun validate(
        callable: UseCaseValidationContext<R>.() -> Unit
    ) {
        val helperCtx = UseCaseValidationContext(request)
        try {
            val capturedException = try {
                callable(helperCtx)
                null
            } catch (e: Throwable) {
                e
            }

            val capturedErrors = helperCtx.validate()

            if (capturedException != null) {
                throw UseCaseValidationError(capturedErrors.toMutableMap().also {
                    it["unhandled-exception"] = listOf(capturedException.message ?: "Unknown error occurred")
                })
            } else if (capturedErrors.isNotEmpty()) {
                throw UseCaseValidationError(capturedErrors)
            }
        } catch (e: Throwable) {
            throw UseCaseResponseError(
                if (e is UseCaseValidationError) {
                    UseCaseErrorHelper.buildValidationError(e.messages, useCaseErrorResponseClass)
                } else {
                    UseCaseErrorHelper.buildUnknownError(e, useCaseErrorResponseClass)
                }
            )
        }
    }
}