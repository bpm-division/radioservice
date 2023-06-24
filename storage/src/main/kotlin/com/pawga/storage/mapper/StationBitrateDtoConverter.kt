package com.pawga.storage.mapper

import com.pawga.storage.model.StationBitrateDTO
import com.pawga.common.service.Converter
import com.pawga.radio_bootify.domain.StationBitrateDb
import org.springframework.stereotype.Service

@Service
class StationBitrateDtoConverter: Converter<StationBitrateDTO, StationBitrateDb> {

    override fun toDto(model: StationBitrateDTO): StationBitrateDb? = ApiMapper.INSTANCE.dtoToEntity(model)
    override fun toModel(dto: StationBitrateDb): StationBitrateDTO = ApiMapper.INSTANCE.entityToDTOv1(dto)
}
