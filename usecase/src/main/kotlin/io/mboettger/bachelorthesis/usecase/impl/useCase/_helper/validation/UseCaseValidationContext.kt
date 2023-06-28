package io.mboettger.bachelorthesis.usecase.impl.useCase._helper.validation

import io.mboettger.bachelorthesis.usecase.boundary.useCase._helper.ValidationErrors
import kotlin.reflect.KProperty0

internal class UseCaseValidationContext<I : Any>(
    private val request: I
) {
    private val validators = mutableListOf<UseCaseValueValidator<*>>()

    private fun replaceValidator(old: UseCaseValueValidator<*>, new: UseCaseValueValidator<*>) {
        validators.remove(old)
        validators.add(new)
    }

    internal fun validate(): ValidationErrors {
        return validators.filter {
            it.messages.isNotEmpty()
        }.associate {
            it.name to it.messages
        }
    }

    fun <T : Any?> property(property: KProperty0<T>) = UseCaseValueValidator(
        property.name,
        property.get(),
        ::replaceValidator,
    ).also { validators.add(it) }

    fun <T : Any?> value(value: T, valueName: String) = UseCaseValueValidator(
        valueName,
        value,
        ::replaceValidator,
    ).also { validators.add(it) }

    fun <T : Any?> extract(extractedName: String, callable: (request: I) -> T) = UseCaseValueValidator(
        extractedName,
        callable(request),
        ::replaceValidator
    ).also { validators.add(it) }
}