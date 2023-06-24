package com.pawga.common.service

/**
 * M - model
 * D - DTO
 */
interface Converter<M, D> {
    fun toDto(model: M): D?
    fun toModel(dto: D): M?
}