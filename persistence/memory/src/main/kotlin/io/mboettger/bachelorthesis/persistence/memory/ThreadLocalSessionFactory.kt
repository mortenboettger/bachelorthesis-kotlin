package io.mboettger.bachelorthesis.persistence.memory

import org.hibernate.Session
import org.hibernate.SessionFactory

internal object ThreadLocalSessionFactory {

    private val currentSession = ThreadLocal<Session>()

    fun getOrCreate(sessionFactory: SessionFactory): Session {
        val localSession = currentSession.get()?.let {
            if(it.isOpen) it else null
        }?:sessionFactory.openSession()

        currentSession.set(localSession)
        return localSession
    }
}