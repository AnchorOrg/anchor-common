package org.anchor.csrSupport

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import java.io.Serializable

open class BaseService<ID : Serializable, M : BaseEntity>
    (private val repositorySupport: JpaRepository<M, ID>) {

    fun upsert(m: M): M = this.repositorySupport.save(m)

    fun saveAndFlush(m: M): M = repositorySupport.saveAndFlush(m)

    fun saveAll(entities: Iterable<M>): List<M> = repositorySupport.saveAll(entities)

    fun findOne(id: ID): M = repositorySupport.findById(id).get()

    fun findAll(): List<M> = repositorySupport.findAll()

    fun findAll(sort: Sort): List<M> = repositorySupport.findAll(sort)

    fun findAll(pageable: Pageable): Page<M> = repositorySupport.findAll(pageable)

    fun deleteAllInBatch(entities: Iterable<M>) = repositorySupport.deleteAllInBatch(entities)

    fun delete(m: M) = repositorySupport.delete(m)

    fun deleteAll() = repositorySupport.deleteAll()

    fun delete(id: ID) = repositorySupport.deleteById(id)

    fun delete(ids: List<ID>) = repositorySupport.deleteAllById(ids)

    fun deleteById(id: ID) = repositorySupport.deleteById(id)

    fun count(): Long = repositorySupport.count()

    fun exists(id: ID): Boolean = repositorySupport.existsById(id)

    fun flush() = repositorySupport.flush()
}
