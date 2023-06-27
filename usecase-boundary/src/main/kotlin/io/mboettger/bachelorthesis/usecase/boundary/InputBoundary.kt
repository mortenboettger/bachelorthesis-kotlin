package io.mboettger.bachelorthesis.usecase.boundary

fun interface InputBoundary<R, P: UseCaseResponse<E>, E: UseCaseResponse<E>> {
    fun execute(request: R, presenter: OutputBoundary<P, E>)
}