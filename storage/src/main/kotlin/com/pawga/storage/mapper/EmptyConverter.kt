package com.pawga.storage.mapper

import com.pawga.common.service.Converter
import org.springframework.stereotype.Service

@Service
class EmptyConverter<M> : Converter<M, M> {
    override fun toDto(model: M): M = model

    override fun toModel(dto: M): M = dto
}