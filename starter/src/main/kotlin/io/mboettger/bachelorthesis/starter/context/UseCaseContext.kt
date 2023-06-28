package io.mboettger.bachelorthesis.starter.context

import io.mboettger.bachelorthesis.persistence.boundary.GatewayFactory
import io.mboettger.bachelorthesis.usecase.boundary.UseCaseFactory
import io.mboettger.bachelorthesis.usecase.impl.factory.UseCaseFactoryImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
internal open class UseCaseContext {

    @Bean
    open fun useCaseFactory(gatewayFactory: GatewayFactory): UseCaseFactory = UseCaseFactoryImpl(gatewayFactory)
}