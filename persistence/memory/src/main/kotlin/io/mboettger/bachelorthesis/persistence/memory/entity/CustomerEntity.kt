package io.mboettger.bachelorthesis.persistence.memory.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Transient

@Entity
@Table(name = "customer")
class CustomerEntity (
    @Transient
    override var id: String? = null,

    @get:Column(
        name = "name",
        length = 255,
        nullable = false,
    )
    var name: String = "",

    @get:Column(
        name = "surname",
        length = 255,
        nullable = false,
    )
    var surName: String = "",

    @get:Column(
        name = "street",
        length = 255,
        nullable = false,
    )
    var street: String = "",

    @get:Column(
        name = "house_number",
        length = 10,
        nullable = false,
    )
    var houseNumber: String = "",

    @get:Column(
        name = "house_number_addition",
        length = 10,
        nullable = true,
    )
    var houseNumberAddition: String? = null,

    @get:Column(
        name = "post_code",
        length = 20,
        nullable = false,
    )
    var postCode: String = "",

    @get:Column(
        name = "city",
        length = 255,
        nullable = false,
    )
    var city: String = "",

    @get:Column(
        name = "district",
        length = 255,
        nullable = true,
    )
    var district: String? = null,

    @get:Column(
        name = "phone_number",
        length = 255,
        nullable = true,
    )
    var phoneNumber: String? = null,

    @get:Column(
        name = "email_address",
        length = 255,
        nullable = true,
    )
    var emailAddress: String? = null,
): EntityModel(id)