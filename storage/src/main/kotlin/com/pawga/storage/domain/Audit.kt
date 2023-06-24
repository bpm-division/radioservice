package com.pawga.storage.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*

@Embeddable
class Audit : Serializable {
    @JvmField
    @Column(name = "created_on", nullable = false)
    var createdOn: LocalDateTime? = null

    @JvmField
    @Column(name = "updated_on", nullable = true)
    var updatedOn: LocalDateTime? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val audit = other as Audit
        return createdOn == audit.createdOn && updatedOn == audit.updatedOn
    }

    override fun hashCode(): Int {
        return Objects.hash(createdOn, updatedOn)
    }
}