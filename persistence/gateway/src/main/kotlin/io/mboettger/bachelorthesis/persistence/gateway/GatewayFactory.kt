package io.mboettger.bachelorthesis.persistence.gateway

import kotlin.reflect.KClass

interface GatewayFactory {

    @Throws(NotImplementedError::class)
    fun <T:Gateway> make(gateway: KClass<out T>): T

    fun migrate()

    operator fun <T:Gateway> get(gateway: KClass<out T>): T = make(gateway)
}

inline fun <reified T : Gateway, G : GatewayFactory> G.get() = make(T::class)

inline operator fun <reified T: Gateway> GatewayFactory.invoke() = make(T::class)