package org.anchor.csrSupport

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.Version
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.springframework.data.domain.Persistable


@MappedSuperclass
abstract class BaseEntity : Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null
    override fun getId(): Long? = id
    @Version
    private val version: Int? = null

    override fun isNew(): Boolean {
        return version == null
    }

    override fun toString(): String {
        return Json.encodeToString(this)
    }
}