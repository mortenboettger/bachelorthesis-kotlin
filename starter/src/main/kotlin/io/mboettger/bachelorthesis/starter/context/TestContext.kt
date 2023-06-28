package io.mboettger.bachelorthesis.starter.context

import io.mboettger.bachelorthesis.domain.customer.Customer
import io.mboettger.bachelorthesis.domain.customer.Name
import io.mboettger.bachelorthesis.domain.customer.SurName
import io.mboettger.bachelorthesis.domain.customer.address.*
import io.mboettger.bachelorthesis.persistence.boundary.CustomerGateway
import io.mboettger.bachelorthesis.persistence.boundary.GatewayFactory
import io.mboettger.bachelorthesis.usecase.boundary.UseCaseFactory
import io.mboettger.bachelorthesis.usecase.boundary.useCase.customer.create.CreateCustomerUseCase
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
internal open class TestContext {
    private val log = LoggerFactory.getLogger(TestContext::class.java)

    @Bean
    open fun testPersistence(gatewayFactory: GatewayFactory) = CommandLineRunner {
        val customer = Customer(
            id = "",
            name = Name("Max"),
            surName = SurName("Mustermann"),
            address = Address(
                street = Street("Musterstraße"),
                houseNumber = HouseNumber("12"),
                postCode = PostCode("12345"),
                city = City("Musterstadt"),
            ),
        )
        val customerGateway = gatewayFactory.get(CustomerGateway::class)
        log.info("Saving $customer")
        val saved = customerGateway.save(customer)
        log.info("Saved $saved")
        log.info("Retrieved ${customerGateway.findOne(saved.id)}")
    }
}