package com.pawga.storage.mapper

import com.pawga.storage.model.StationDTO
import com.pawga.common.service.Converter
import com.pawga.storage.domain.StationDb
import org.springframework.stereotype.Service

@Service
class StationDtoConverter: Converter<StationDTO, StationDb> {

    override fun toDto(model: StationDTO): StationDb = ApiMapper.INSTANCE.dtoToEntity(model)
    override fun toModel(dto: StationDb): StationDTO = ApiMapper.INSTANCE.entityToDTO(dto)
}
