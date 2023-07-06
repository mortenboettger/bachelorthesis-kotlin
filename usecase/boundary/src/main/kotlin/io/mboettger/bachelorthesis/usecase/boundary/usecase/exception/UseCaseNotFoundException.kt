package io.mboettger.bachelorthesis.usecase.boundary.usecase.exception

class UseCaseNotFoundException(message: String, throwable: Throwable? = null) : Throwable(message, throwable)