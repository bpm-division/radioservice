package com.pawga.storage.service

import com.pawga.storage.domain.StationTypeDb
import com.pawga.storage.model.StationTypeDTO
import com.pawga.storage.mapper.StationTypeDtoConverter
import com.pawga.storage.repository.StationTypeRepository
import org.springframework.stereotype.Service

@Service
class StationTypeDbStorageService(
    repository: StationTypeRepository,
    converter: StationTypeDtoConverter
) : DbStorageService<StationTypeDTO, StationTypeDb, Long>(repository, converter)
