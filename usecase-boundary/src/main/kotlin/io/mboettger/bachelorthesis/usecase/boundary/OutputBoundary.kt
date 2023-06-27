package io.mboettger.bachelorthesis.usecase.boundary

fun interface OutputBoundary<R : UseCaseResponse<E>, E: UseCaseResponse<E>> {
    fun present(response: R)
}