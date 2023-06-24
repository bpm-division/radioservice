package com.pawga.storage.repository

import com.pawga.storage.domain.StationTypeDb
import org.springframework.data.jpa.repository.JpaRepository


interface StationTypeRepository : JpaRepository<StationTypeDb, Long> {
    fun existsByNameIgnoreCase(name: String?): Boolean

}
