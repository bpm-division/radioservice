package com.pawga.storage.domain

import com.pawga.common.model.Keyed
import jakarta.persistence.*
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.util.*

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "locale")
class LocaleDb : Keyed<Long>, Serializable {

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

    @Column(
        nullable = false,
        unique = true
    )
    var name: String? = null

    constructor()
    constructor(
        id: Long?,
        name: String?,
    ) {
        this.id = id
        this.name = name
    }

    override fun getKey(): Long = id ?: 0

    override fun setKey(key: Long): Boolean {
        id = key
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        if (!super.equals(other)) return false
        val locale = other as LocaleDb
        return id == locale.id
                && name == locale.name

    }

    override fun hashCode(): Int {
        return Objects.hash(id, name)
    }
}