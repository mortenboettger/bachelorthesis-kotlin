package io.mboettger.bachelorthesis.persistence.memory

import io.mboettger.bachelorthesis.domain.customer.*
import io.mboettger.bachelorthesis.domain.customer.address.*
import io.mboettger.bachelorthesis.persistence.gateway.CustomerGateway
import io.mboettger.bachelorthesis.persistence.memory.entity.CustomerEntity
import org.hibernate.SessionFactory

internal class CustomerGatewayImpl(
    sessionFactory: SessionFactory,
) : ReadWriteGatewayImpl<Customer, CustomerEntity>(
    sessionFactory,
    CustomerEntity::class,
), CustomerGateway {
    override fun Customer.toEntity() = CustomerEntity(
        id = id.ifBlank { null },
        firstName = firstName.value,
        lastName = lastName.value,
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
        firstName = FirstName(firstName),
        lastName = LastName(lastName),
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

    override fun findByEmail(emailAddress: EmailAddress): Customer? {
        return withTransaction {
            queryWithCriteria {
                val root = from(entityClass.java)
                where(
                    root.get<String?>(CustomerEntity::emailAddress.name).`in`(emailAddress.value)
                )
            }
        }.resultList.firstOrNull()?.toDomain()
    }

    override fun existsByEmail(emailAddress: EmailAddress): Boolean =
        findByEmail(emailAddress) != null
}