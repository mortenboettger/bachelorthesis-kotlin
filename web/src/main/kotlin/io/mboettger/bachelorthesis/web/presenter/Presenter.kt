package io.mboettger.bachelorthesis.web.presenter

import io.mboettger.bachelorthesis.usecase.boundary.OutputBoundary
import io.mboettger.bachelorthesis.usecase.boundary.UseCaseResponse
import io.mboettger.bachelorthesis.usecase.boundary.useCase.UseCaseErrorMessageContainer
import io.mboettger.bachelorthesis.usecase.boundary.useCase.UseCaseUnknownErrorContainer
import org.springframework.http.HttpStatus
import org.springframework.web.client.HttpServerErrorException
import java.lang.ClassCastException

internal interface Presenter<Q, R : UseCaseResponse<E>, S : R, E : R> : OutputBoundary<R, E> {
    fun webResponse(): Q

    fun success(response: S)

    fun failure(response: E)

    override fun present(response: R) {
        if (response.success) {
            try {
                @Suppress("UNCHECKED_CAST")
                success(response as S)
            } catch (e: ClassCastException) {
                throw IllegalStateException(
                    "The request was successfully handled internally but ${Presenter::class.simpleName} is unable to cast a useCase response of type ${response::class.simpleName} to the declared one. Concrete presenter generic parameters may be wrong",
                    e
                )
            }
        } else {
            if (response is UseCaseUnknownErrorContainer && response is UseCaseErrorMessageContainer && response.error is NotImplementedError) {
                throw HttpServerErrorException(HttpStatus.NOT_IMPLEMENTED, response.message)
            }

            try {
                @Suppress("UNCHECKED_CAST")
                failure(response as E)
            } catch (e: ClassCastException) {
                throw IllegalStateException(
                    "The request failed and the ${Presenter::class.simpleName} is unable to cast a useCase response of type ${response::class.simpleName} to the declared one. Concrete presenter generic parameters may be wrong",
                    e
                )
            }
        }
    }
}