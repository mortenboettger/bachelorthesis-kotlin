package io.mboettger.bachelorthesis.usecase.impl.usecase.helper

import io.mboettger.bachelorthesis.usecase.boundary.InputBoundary
import io.mboettger.bachelorthesis.usecase.boundary.UseCaseResponse
import io.mboettger.bachelorthesis.usecase.impl.usecase.helper.context.UseCaseContext
import io.mboettger.bachelorthesis.usecase.impl.usecase.helper.error.UseCaseErrorHelper
import io.mboettger.bachelorthesis.usecase.impl.usecase.helper.error.UseCaseValidationError
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Proxy

internal inline fun <reified I : InputBoundary<T, R, E>, T : Any, reified R : UseCaseResponse<E>, reified E : R> useCase(
    crossinline validationCallable: UseCaseContext<T>.() -> Unit,
    crossinline executionCallable: UseCaseContext<T>.() -> R
): I {
    val inputBoundary = InputBoundary<T, R, E> { request, presenter ->
        val ctx = UseCaseContext(request)

        try {
            validationCallable(ctx)
        } catch (e: IllegalStateException) {
            presenter.present(UseCaseErrorHelper.buildValidationError(e.message, E::class))
            return@InputBoundary
        } catch (e: Throwable) {
            UseCaseErrorHelper.buildUnknownError(e, E::class)
            return@InputBoundary
        }

        presenter.present(
            try {
                executionCallable(ctx)
            } catch (e: Throwable) {
                UseCaseErrorHelper.buildUnknownError(e, E::class)
            }
        )
    }

    return Proxy.newProxyInstance(I::class.java.classLoader, arrayOf(I::class.java)) { _, method, args ->
        try {
            method.invoke(inputBoundary, *args)
        } catch (e: InvocationTargetException) {
            throw e.cause
                ?: IllegalStateException("Unable to extract thrown exception from the underlying context.")
        }
    } as I
}
