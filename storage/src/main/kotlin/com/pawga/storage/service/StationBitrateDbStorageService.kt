package com.pawga.storage.service

import com.pawga.storage.model.StationBitrateDTO
import com.pawga.radio_bootify.domain.StationBitrateDb
import com.pawga.radio_bootify.repos.StationBitrateDbRepository
import com.pawga.storage.mapper.StationBitrateDtoConverter
import org.springframework.stereotype.Service

@Service
class StationBitrateDbStorageService(
    repository: StationBitrateDbRepository,
    converter: StationBitrateDtoConverter
) : DbStorageService<StationBitrateDTO, StationBitrateDb, Long>(repository, converter)
