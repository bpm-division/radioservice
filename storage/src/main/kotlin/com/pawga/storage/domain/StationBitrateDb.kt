package com.pawga.radio_bootify.domain

import com.pawga.common.model.Keyed
import com.pawga.storage.domain.StationDb
import jakarta.persistence.*
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.util.*


@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "station_bitrate")
class StationBitrateDb : Keyed<Long>, Serializable {

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

    @Column(nullable = false)
    var bitrate: Int? = null

    @Column(nullable = false)
    var stream: String? = null

    constructor()
    constructor(
        id: Long?,
        bitrate:  Int?,
        stream:  String?,
    ) {
        this.id = id
        this.bitrate = bitrate
        this.stream = stream
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "station_id",
        nullable = false
    )
    var station: StationDb? = null

    override fun getKey(): Long = id ?: 0

    override fun setKey(key: Long): Boolean {
        id = key
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        if (!super.equals(other)) return false
        val obj = other as StationBitrateDb
        return id == obj.id && bitrate == obj.bitrate && stream == obj.stream

    }

    override fun hashCode(): Int {
        return Objects.hash(id, bitrate, stream)
    }
}
