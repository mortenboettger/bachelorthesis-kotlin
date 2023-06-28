package io.mboettger.bachelorthesis.web.presenter

import io.mboettger.bachelorthesis.usecase.boundary.UseCaseResponse
import org.springframework.http.HttpStatus
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

internal abstract class RestPresenter<Q, R : UseCaseResponse<E>, S : R, E : R> : Presenter<Q, R, S, E> {
    protected fun setResponseStatus(status: HttpStatus) {
        RequestContextHolder.getRequestAttributes()?.let {
            if (it is ServletRequestAttributes) {
                it.response?.status = status.value()
            }
        }
    }
}