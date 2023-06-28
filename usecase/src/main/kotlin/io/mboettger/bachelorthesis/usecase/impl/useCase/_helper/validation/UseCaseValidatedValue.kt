package io.mboettger.bachelorthesis.usecase.impl.useCase._helper.validation

internal class UseCaseValidatedValue<T : Any?>(
    private val validator: UseCaseValueValidator<T>,
    internal val callable: () -> Boolean,
) {
    infix fun or(callable: (UseCaseValueValidator<T>) -> UseCaseValidatedValue<T>) = UseCaseValidatedValue(validator) {
        this.callable() || callable(validator).callable()
    }

    infix fun and(callable: (UseCaseValueValidator<T>) -> UseCaseValidatedValue<T>) = UseCaseValidatedValue(validator) {
        this.callable() && callable(validator).callable()
    }

    infix fun xor(callable: (UseCaseValueValidator<T>) -> UseCaseValidatedValue<T>) = UseCaseValidatedValue(validator) {
        this.callable() xor callable(validator).callable()
    }

    infix fun orReject(message: String) = if (!callable())
        validator.reject(message) else Unit
}