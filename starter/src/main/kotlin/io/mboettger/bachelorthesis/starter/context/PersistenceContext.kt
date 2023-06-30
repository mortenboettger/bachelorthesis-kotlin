package io.mboettger.bachelorthesis.starter.context

import io.mboettger.bachelorthesis.persistence.gateway.GatewayFactory
import io.mboettger.bachelorthesis.persistence.memory.MemoryGatewayFactoryImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.AbstractEnvironment
import org.springframework.core.env.EnumerablePropertySource
import org.springframework.core.env.Environment
import java.util.*

@Configuration
internal open class PersistenceContext(private val env: Environment) {

    private val properties: Properties by lazy {
        (env as AbstractEnvironment).propertySources
            .filterIsInstance<EnumerablePropertySource<*>>()
            .flatMap { it.propertyNames.toList() }
            .associateWith { env.getProperty(it) ?: "" }
            .toProperties()
    }

    @Bean
    open fun gatewayFactory(): GatewayFactory = MemoryGatewayFactoryImpl(properties).apply {
        migrate()
    }
}