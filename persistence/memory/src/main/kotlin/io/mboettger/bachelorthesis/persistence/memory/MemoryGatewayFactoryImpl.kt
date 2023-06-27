package io.mboettger.bachelorthesis.persistence.memory

import io.mboettger.bachelorthesis.persistence.boundary.CustomerGateway
import io.mboettger.bachelorthesis.persistence.boundary.Gateway
import io.mboettger.bachelorthesis.persistence.boundary.GatewayFactory
import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence
import org.flywaydb.core.Flyway
import org.hibernate.Session
import org.hibernate.SessionFactory
import java.util.*
import kotlin.reflect.KClass

class MemoryGatewayFactoryImpl(
    private val properties: Properties,
) : GatewayFactory {

    private val entityManagerFactory : EntityManagerFactory by lazy { Persistence.createEntityManagerFactory(
        "bachelorthesis-persistence",
        properties
    ) }

    private val entityManager: EntityManager by lazy {
        entityManagerFactory.createEntityManager()
    }
    private val sessionFactory: SessionFactory by lazy {
        entityManager.unwrap(Session::class.java).sessionFactory
    }
    private val flyway: Flyway by lazy {
        Flyway.configure().dataSource(
            properties["hibernate.connection.url"] as String,
            properties["hibernate.connection.username"] as String,
            properties["hibernate.connection.password"] as String
        ).baselineOnMigrate(true)
            .locations("classpath:/sql-migration")
            .load()
    }

    private val baseGateways: Map<KClass<out Gateway>, Gateway> by lazy {
        mapOf(
            CustomerGateway::class to CustomerGatewayImpl(entityManager,sessionFactory)
        )
    }
    private val gateways: Map<KClass<out Gateway>, Gateway> = mapOf()

    private val mergedGateways : Map<KClass<out Gateway>, Gateway> by lazy {
        baseGateways + gateways
    }

    override fun <T : Gateway> make(gateway: KClass<out T>): T {
        @Suppress("UNCHECKED_CAST")
        return mergedGateways[gateway] as? T ?: throw NotImplementedError("Unable to find requested gateway \"${gateway.simpleName}\" within this gatewayFactory")
    }

    override fun migrate() {
        flyway.migrate()
    }
}