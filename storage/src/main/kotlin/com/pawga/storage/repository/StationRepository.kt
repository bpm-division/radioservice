package com.pawga.storage.repository

import com.pawga.storage.domain.StationDb
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by sivannikov on 19.06.2023 17:54
 */

interface StationRepository : JpaRepository<StationDb, Long>