package io.mboettger.bachelorthesis.usecase.impl.useCase.customer

import io.mboettger.bachelorthesis.domain.customer.*
import io.mboettger.bachelorthesis.domain.customer.address.*
import io.mboettger.bachelorthesis.persistence.boundary.CustomerGateway
import io.mboettger.bachelorthesis.usecase.boundary.useCase.customer.create.CreateCustomerResponse
import io.mboettger.bachelorthesis.usecase.boundary.useCase.customer.create.CreateCustomerUseCase
import io.mboettger.bachelorthesis.usecase.impl.useCase._helper.useCase

internal class CreateCustomerUseCaseImpl(
    private val customerGateway: CustomerGateway
) : CreateCustomerUseCase by useCase({
    val customer = Customer(
        id = "",
        name = Name(request.name),
        surName = SurName(request.surName),
        address = Address(
            street = Street(request.address.street),
            houseNumber = HouseNumber(request.address.houseNumber),
            houseNumberAddition = request.address.houseNumberAddition?.let { HouseNumberAddition(it) },
            postCode = PostCode(request.address.postCode),
            city = City(request.address.city),
            district = request.address.district?.let { District(it) },
        ),
        phoneNumber = request.phoneNumber?.let { PhoneNumber(it) },
        emailAddress = request.emailAddress?.let { EmailAddress(it) },
    )

    val saved = customerGateway.save(customer)

    CreateCustomerResponse.Success(
        saved.id
    )
})