package com.pawga.storage.mapper

import com.pawga.storage.model.StationTypeDTO
import com.pawga.common.service.Converter
import com.pawga.storage.domain.StationTypeDb
import org.springframework.stereotype.Service

@Service
class StationTypeDtoConverter: Converter<StationTypeDTO, StationTypeDb> {

    override fun toDto(model: StationTypeDTO): StationTypeDb? = ApiMapper.INSTANCE.dtoToEntity(model)
    override fun toModel(dto: StationTypeDb): StationTypeDTO = ApiMapper.INSTANCE.entityToDTOv1(dto)
}
