package com.pawga.storage.service

import com.pawga.storage.model.StationDTO
import com.pawga.storage.domain.StationDb
import com.pawga.storage.mapper.StationDtoConverter
import com.pawga.storage.repository.StationRepository
import org.springframework.stereotype.Service

@Service
class StationDbStorageService(
    repository: StationRepository,
    converter: StationDtoConverter
) : DbStorageService<StationDTO, StationDb, Long>(repository, converter)
