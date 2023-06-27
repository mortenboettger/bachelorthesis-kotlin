package io.mboettger.bachelorthesis.persistence.memory

import io.mboettger.bachelorthesis.domain.customer.*
import io.mboettger.bachelorthesis.domain.customer.address.*
import io.mboettger.bachelorthesis.persistence.boundary.CustomerGateway
import io.mboettger.bachelorthesis.persistence.memory.entity.CustomerEntity
import jakarta.persistence.EntityManager
import jakarta.persistence.criteria.Path
import jakarta.persistence.criteria.Predicate
import org.hibernate.SessionFactory
import java.util.stream.Stream

internal class CustomerGatewayImpl(
    entityManager: EntityManager,
    sessionFactory: SessionFactory,
) : ReadWriteGatewayImpl<Customer, CustomerEntity>(
    entityManager,
    sessionFactory,
    CustomerEntity::class,
), CustomerGateway {
    override fun Customer.toEntity() = CustomerEntity(
        id = id.ifBlank { null },
        name = name.value,
        surName = surName.value,
        street = address.street.value,
        houseNumber = address.houseNumber.value,
        houseNumberAddition = address.houseNumberAddition?.value,
        postCode = address.postCode.value,
        city = address.city.value,
        district = address.district?.value,
        emailAddress = emailAddress?.value,
        phoneNumber = phoneNumber?.value,
    )

    override fun CustomerEntity.toDomain() = Customer(
        id = ::id.getOrThrow(),
        name = Name(name),
        surName = SurName(surName),
        address = Address(
            street = Street(street),
            houseNumber = HouseNumber(houseNumber),
            houseNumberAddition = houseNumberAddition?.let { HouseNumberAddition(it) },
            postCode = PostCode(postCode),
            city = City(city),
            district = district?.let { District(it) },
        ),
        emailAddress = emailAddress?.let { EmailAddress(it) },
        phoneNumber = phoneNumber?.let { PhoneNumber(it) },
    )

    override fun findCustomerByEmail(emailAddress: EmailAddress): Customer? {
        return withTransaction {
            queryWithCriteria {
                val root = from(entityClass.java)
                where(
                    root.get<String?>(CustomerEntity::emailAddress.name).`in`(emailAddress.value)
                )
            }
        }.resultList.firstOrNull()?.toDomain()
    }

    override fun findCustomersByAddress(address: Address): Stream<Customer> {
        return withTransaction {
            queryWithCriteria {
                val root = from(entityClass.java)
                where(
                    root.get<String>(CustomerEntity::street.name).`in`(address.street.value),
                    root.get<String>(CustomerEntity::houseNumber.name).`in`(address.houseNumber.value),
                    root.get<String?>(CustomerEntity::houseNumberAddition.name).isNullOrEquals(address.houseNumberAddition?.value),
                    root.get<String>(CustomerEntity::postCode.name).`in`(address.postCode.value),
                    root.get<String>(CustomerEntity::city.name).`in`(address.city.value),
                    root.get<String?>(CustomerEntity::district.name).isNullOrEquals(address.district?.value),
                )
            }
        }.resultStream.map { it.toDomain() }
    }

    private fun <T> Path<T>.isNullOrEquals(value: T): Predicate {
        return if(value == null) {
            isNull
        }else {
            `in`(value)
        }
    }
}