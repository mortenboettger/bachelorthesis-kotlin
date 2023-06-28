package io.mboettger.bachelorthesis.usecase.impl.useCase._helper

import io.mboettger.bachelorthesis.usecase.boundary.InputBoundary
import io.mboettger.bachelorthesis.usecase.boundary.UseCaseResponse
import io.mboettger.bachelorthesis.usecase.impl.useCase._helper.context.UseCaseContext
import io.mboettger.bachelorthesis.usecase.impl.useCase._helper.error.UseCaseErrorHelper
import io.mboettger.bachelorthesis.usecase.impl.useCase._helper.error.UseCaseResponseError
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Proxy

internal inline fun <reified I : InputBoundary<T, R, E>, T : Any, reified R : UseCaseResponse<E>, reified E : R> useCase(
    crossinline callable: UseCaseContext<T, E>.() -> R
): I {
    val inputBoundary = InputBoundary<T, R, E> { request, presenter ->
        val ctx = UseCaseContext(request, E::class)
        try {
            presenter.present(callable(ctx))
        } catch (e: Throwable) {
            presenter.present(
                if (e is UseCaseResponseError) {
                    e.response
                } else {
                    UseCaseErrorHelper.buildUnknownError(e, E::class)
                } as R
            )
        }
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
