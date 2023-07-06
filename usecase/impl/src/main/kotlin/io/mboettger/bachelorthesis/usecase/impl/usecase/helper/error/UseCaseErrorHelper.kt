package io.mboettger.bachelorthesis.usecase.impl.usecase.helper.error

import io.mboettger.bachelorthesis.usecase.boundary.UseCaseResponse
import io.mboettger.bachelorthesis.usecase.boundary.usecase.UseCaseUnknownErrorContainer
import io.mboettger.bachelorthesis.usecase.boundary.usecase.UseCaseValidationErrorContainer
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.jvm.jvmErasure

internal object UseCaseErrorHelper {
    fun <T : UseCaseResponse<T>> buildUnknownError(error: Throwable, useCaseResponseErrorClass: KClass<out T>): T {

        val errorCtor = if (useCaseResponseErrorClass.isSealed) {
            useCaseResponseErrorClass.sealedSubclasses.mapNotNull {
                findCtorByParamClass(it, Throwable::class)
            }
        } else {
            listOfNotNull(
                findCtorByParamClass(useCaseResponseErrorClass, Throwable::class)
            )
        }

        return errorCtor.firstOrNull()?.call(error).also {
            if (it !is UseCaseUnknownErrorContainer) {
                throw IllegalStateException("An unknown error use case response class must implement ${UseCaseUnknownErrorContainer::class.simpleName}")
            }
        }
            ?: throw IllegalStateException("Unable to construct unknown error response class because ${useCaseResponseErrorClass.qualifiedName} does not have a constructor with a single parameter of type ${Throwable::class.simpleName}")

    }

    fun <T : UseCaseResponse<T>> buildValidationError(error: String?, useCaseResponseErrorClass: KClass<out T>): T {

        val validationCtor = if (useCaseResponseErrorClass.isSealed) {
            useCaseResponseErrorClass.sealedSubclasses.mapNotNull {
                // map class references ValidationErrors alias
                findCtorByParamClass(it, Map::class)
            }
        } else {
            listOfNotNull(
                // map class references ValidationErrors alias
                findCtorByParamClass(useCaseResponseErrorClass, Map::class)
            )
        }

        return validationCtor.firstOrNull {
            it.returnType.jvmErasure.isSubclassOf(UseCaseValidationErrorContainer::class)
        }?.call(error ?: "Unknown validation error").also {
            check(it is UseCaseValidationErrorContainer) { "A validation error use case response class must implement ${UseCaseValidationErrorContainer::class.simpleName}" }
        }
            ?: throw IllegalStateException("Unable to construct validation response class because ${useCaseResponseErrorClass.qualifiedName} does not have a constructor with one parameter of type: String")

    }

    private fun <T : UseCaseResponse<T>> findCtorByParamClass(clazz: KClass<out T>, paramClass: KClass<*>): KFunction<T>? {
        return clazz.constructors.firstOrNull() {
            it.parameters.firstOrNull()?.type?.jvmErasure == paramClass
        }
    }
}