package io.mboettger.bachelorthesis.web._helper

import io.mboettger.bachelorthesis.usecase.boundary.InputBoundary
import io.mboettger.bachelorthesis.usecase.boundary.UseCaseFactory
import io.mboettger.bachelorthesis.usecase.boundary.UseCaseResponse
import io.mboettger.bachelorthesis.usecase.boundary.useCase.UseCaseErrorMessageContainer
import io.mboettger.bachelorthesis.usecase.boundary.useCase.UseCaseValidationErrorContainer
import io.mboettger.bachelorthesis.web.presenter.Presenter
import org.springframework.http.HttpStatus
import org.springframework.web.client.HttpServerErrorException
import kotlin.reflect.KClass

internal fun <I : InputBoundary<R, P, E>, R, P : UseCaseResponse<E>, E : UseCaseResponse<E>, O : Presenter<Q, P, S, E>, Q, S : P> UseCaseFactory.execute(
    useCase: KClass<out I>,
    presenter: O,
    request: () -> R
): Q {
    get(useCase).execute(request.invoke(), presenter)
    return presenter.webResponse()
}

fun <E> E.asInternalServerError(): Nothing where E : UseCaseResponse<E>, E : UseCaseErrorMessageContainer{
    throw HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, message)
}

fun UseCaseValidationErrorContainer.asValidationError(): Nothing {
    throw HttpServerErrorException(HttpStatus.BAD_REQUEST, validationErrors.toString())
}

fun <E> E.throwWithStatus(status: HttpStatus): Nothing where E : UseCaseResponse<E>, E : UseCaseErrorMessageContainer{
    throw HttpServerErrorException(status, message)
}
