package com.pawga.storage.mapper

import com.pawga.storage.model.StationDTOv1
import com.pawga.common.service.Converter
import com.pawga.storage.domain.StationDb
import org.springframework.stereotype.Service

@Service
class StationDtoV1Converter: Converter<StationDTOv1, StationDb> {

    override fun toDto(model: StationDTOv1): StationDb = ApiMapper.INSTANCE.dtoToEntity(model)
    override fun toModel(dto: StationDb): StationDTOv1 = ApiMapper.INSTANCE.entityToDTOv1(dto)
}
