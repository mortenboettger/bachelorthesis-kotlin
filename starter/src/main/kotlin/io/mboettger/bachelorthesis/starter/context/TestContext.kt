package io.mboettger.bachelorthesis.starter.context

import io.mboettger.bachelorthesis.domain.customer.Customer
import io.mboettger.bachelorthesis.domain.customer.FirstName
import io.mboettger.bachelorthesis.domain.customer.LastName
import io.mboettger.bachelorthesis.domain.customer.address.*
import io.mboettger.bachelorthesis.persistence.gateway.CustomerGateway
import io.mboettger.bachelorthesis.persistence.gateway.GatewayFactory
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
            firstName = FirstName("Max"),
            lastName = LastName("Mustermann"),
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