package io.mboettger.bachelorthesis.persistence.boundary

import kotlin.reflect.KClass

interface GatewayFactory {

    @Throws(NotImplementedError::class)
    fun <T:Gateway> make(gateway: KClass<out T>): T

    fun migrate()

    operator fun <T:Gateway> get(gateway: KClass<out T>): T = make(gateway)

    operator fun <T:Gateway> invoke(gateway: KClass<out T>): T = make(gateway)
}

inline operator fun <reified T: Gateway> GatewayFactory.invoke(): T {
    return make(T::class)
}