package io.mboettger.bachelorthesis.persistence.memory

import io.mboettger.bachelorthesis.domain.DomainModel
import io.mboettger.bachelorthesis.persistence.boundary.ReadWriteGateway
import io.mboettger.bachelorthesis.persistence.memory.entity.EntityModel
import jakarta.persistence.EntityManager
import jakarta.persistence.EntityNotFoundException
import jakarta.persistence.TypedQuery
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import org.hibernate.Session
import org.hibernate.SessionFactory
import java.util.stream.Stream
import kotlin.reflect.KClass
import kotlin.reflect.KProperty0

abstract class ReadWriteGatewayImpl<T: DomainModel, E: EntityModel>(
    private val entityManager: EntityManager,
    protected val sessionFactory: SessionFactory,
    protected val entityClass: KClass<out E>
) : ReadWriteGateway<T> {

    protected abstract fun T.toEntity(): E
    protected abstract fun E.toDomain(): T

    override fun findOne(id: String): T {
        return findOneOrNull(id) ?: throw EntityNotFoundException("Unable to find a \"${entityClass.simpleName}\" by given id \"$id\"")
    }

    override fun findOneOrNull(id: String): T? {
        return withTransaction {
            find(entityClass.java, id)
        }?.toDomain()
    }

    override fun findAll(): Stream<T> {
        return withTransaction {
            queryWithCriteria {
                select(from(entityClass.java))
            }
        }.resultStream.map { it.toDomain() }
    }

    override fun save(data: T): T {
        return withTransaction {
            (merge(data.toEntity())).toDomain()
        }
    }

    override fun delete(id: String) {
        withTransaction {
            findOneOrNull(id)?.also {
                remove(it)
            }
        }
    }

    override fun delete(data: T) {
        delete(data.id)
    }

    override fun exists(id: String): Boolean {
        return findOneOrNull(id) != null
    }

    protected fun <R> Session.query(callable: CriteriaBuilder.() -> CriteriaQuery<R>): TypedQuery<R> {
        return createQuery(callable(criteriaBuilder))
    }

    protected fun <R> Session.queryWithCriteria(callable: CriteriaQuery<E>.() -> CriteriaQuery<R>): TypedQuery<R> {
        return query {
            @Suppress("UNCHECKED_CAST")
            callable(createQuery(entityClass.java as Class<E>))
        }
    }

    protected fun <T> withTransaction(callable: Session.() -> T): T {

        val session = ThreadLocalSessionFactory.getOrCreate(sessionFactory)
        return if(session.transaction.isActive) {
            callable(session)
        }else {
            session.transaction.begin()
            try{
                callable(session).also {
                    session.transaction.commit()
                }
            }catch (e: Throwable) {
                if(session.transaction.isActive) {
                    session.transaction.rollback()
                }
                throw e
            }
        }
    }

    protected fun <R> KProperty0<R>.getOrThrow(): R & Any {
        return get() ?: throw NullPointerException("Unable to get required property $name from object ${entityClass.simpleName} because the persistence layer returned null")
    }
}