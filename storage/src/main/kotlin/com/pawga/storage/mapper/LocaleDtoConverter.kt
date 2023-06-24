package com.pawga.storage.mapper

import com.pawga.common.service.Converter
import com.pawga.storage.domain.LocaleDb
import com.pawga.storage.model.LocaleDTO
import org.springframework.stereotype.Service

@Service
class LocaleDtoConverter : Converter<LocaleDTO, LocaleDb> {
    override fun toDto(model: LocaleDTO): LocaleDb? = ApiMapper.INSTANCE.dtoToEntity(model)
    override fun toModel(dto: LocaleDb): LocaleDTO? = ApiMapper.INSTANCE.entityToDTOv1(dto)
}