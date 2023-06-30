package io.mboettger.bachelorthesis.usecase.boundary

import kotlin.reflect.KClass

interface UseCaseFactory {
    operator fun <R, P: UseCaseResponse<E>, Q: InputBoundary<R, P, E>, E: UseCaseResponse<E>> get(inputBoundary: KClass<out Q>): Q
}