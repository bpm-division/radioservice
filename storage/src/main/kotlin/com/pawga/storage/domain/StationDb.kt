package com.pawga.storage.domain

import com.pawga.common.model.RadioStatus
import com.pawga.radio_bootify.domain.StationBitrateDb
import jakarta.persistence.*
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "station")
class StationDb : Base {

    @Column(nullable = false)
    var name: String? = null

    @Column(nullable = false)
    var description: String? = null

    @Column(nullable = false)
    var image: String? = null

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var status: RadioStatus? = null

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "locale_db_id",
        unique = true
    )
    var localeDb: LocaleDb? = null

    @ManyToMany(mappedBy = "stations", fetch = FetchType.LAZY)
    var types: MutableSet<StationTypeDb>? = null

    @OneToMany(mappedBy = "station", fetch = FetchType.LAZY)
    var streams: MutableSet<StationBitrateDb>? = null

    constructor()
    constructor(
        id: Long?,
        name: String?,
        description: String?,
        image: String?,
        status: RadioStatus?,
        localeDb: LocaleDb?,
        types: MutableSet<StationTypeDb>?,
        streams: MutableSet<StationBitrateDb>?
    ) : super(id) {
        this.name = name
        this.description = description
        this.image = image
        this.status = status
        this.localeDb = localeDb
        this.types = types
        this.streams = streams
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        if (!super.equals(other)) return false
        val station = other as StationDb
        return name == station.name
                && description == station.description
                && image == station.image
                && status == station.status
                && localeDb == station.localeDb
    }

    override fun hashCode(): Int {
        return Objects.hash(super.hashCode(), name, description, image, status, localeDb)
    }
}
