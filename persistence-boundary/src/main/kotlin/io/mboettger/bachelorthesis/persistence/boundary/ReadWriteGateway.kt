package io.mboettger.bachelorthesis.persistence.boundary

import io.mboettger.bachelorthesis.domain.DomainModel
import java.util.stream.Stream

interface ReadWriteGateway<T : DomainModel> : Gateway {

    fun findOne(id: String): T

    fun findOneOrNull(id: String): T?

    fun findAll(): Stream<T>

    fun save(data: T): T

    fun delete(id: String)

    fun delete(data: T)

    fun exists(id: String): Boolean
}