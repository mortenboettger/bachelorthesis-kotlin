package io.mboettger.bachelorthesis.web._helper

import io.mboettger.bachelorthesis.usecase.boundary.InputBoundary
import io.mboettger.bachelorthesis.usecase.boundary.UseCaseFactory
import io.mboettger.bachelorthesis.usecase.boundary.UseCaseResponse
import io.mboettger.bachelorthesis.web.presenter.Presenter
import kotlin.reflect.KClass

internal fun <I : InputBoundary<R, P, E>, R, P : UseCaseResponse<E>, E : UseCaseResponse<E>, O : Presenter<Q, P, S, E>, Q, S : P> UseCaseFactory.execute(
    useCase: KClass<out I>,
    presenter: O,
    request: () -> R
): Q {
    get(useCase).execute(request.invoke(), presenter)
    return presenter.webResponse()
}