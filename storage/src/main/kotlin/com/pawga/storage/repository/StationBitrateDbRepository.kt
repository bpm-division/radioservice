package com.pawga.radio_bootify.repos

import com.pawga.radio_bootify.domain.StationBitrateDb
import org.springframework.data.jpa.repository.JpaRepository


interface StationBitrateDbRepository : JpaRepository<StationBitrateDb, Long>
