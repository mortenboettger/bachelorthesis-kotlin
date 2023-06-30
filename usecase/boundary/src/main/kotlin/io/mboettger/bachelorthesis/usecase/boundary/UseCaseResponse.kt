package io.mboettger.bachelorthesis.usecase.boundary

interface UseCaseResponse<E : UseCaseResponse<E>> {
    val success: Boolean
}