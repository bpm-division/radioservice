package com.pawga.storage.domain

import com.pawga.common.model.Keyed
import jakarta.persistence.*
import org.springframework.data.jpa.domain.support.AuditingEntityListener


@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "station_type")
class StationTypeDb : Keyed<Long> {

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

    @ManyToMany
    @JoinTable(
        name = "radio_types",
        joinColumns = [JoinColumn(name = "station_type_id")],
        inverseJoinColumns = [JoinColumn(name = "station_id")]
    )
    var stations: MutableSet<StationDb>? = null

    override fun getKey(): Long = id ?: 0

    override fun setKey(key: Long): Boolean {
        id = key
        return true
    }
}
