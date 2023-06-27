package io.mboettger.bachelorthesis.persistence.memory.entity

import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.GenericGenerator

@MappedSuperclass
abstract class EntityModel(
    @get:Id
    @get:GeneratedValue(generator = "UUID")
    @get:GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    open val id: String?
)