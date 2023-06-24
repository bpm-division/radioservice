package com.pawga.storage.domain

import com.pawga.common.model.Keyed
import jakarta.persistence.*
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*

@MappedSuperclass
abstract class Base : Keyed<Long>, Serializable {
    @Id
    @Column(
        nullable = false,
        updatable = false
    )
    @SequenceGenerator(
        name = "primary_sequence",
        sequenceName = "primary_sequence",
        allocationSize = 1,
        initialValue = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "primary_sequence"
    )
    var id: Long? = null

    @Embedded
    var audit: Audit? = null

    constructor()
    constructor(id: Long?) {
        this.id = id
    }

    @PrePersist
    fun fillCreatedOn() {
        audit?.createdOn = LocalDateTime.now()
    }

    @PreUpdate
    fun fillUpdatedOn() {
        audit?.updatedOn = LocalDateTime.now()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val base = other as Base
        return id == base.id && audit == base.audit
    }

    override fun hashCode(): Int {
        return Objects.hash(id, audit)
    }

    override fun getKey(): Long = id ?: 0

    override fun setKey(key: Long): Boolean {
        id = key
        return true
    }
}
