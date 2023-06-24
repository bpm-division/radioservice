package com.pawga.storage.mapper

import com.pawga.common.model.*
import com.pawga.radio_bootify.domain.StationBitrateDb
import com.pawga.storage.domain.Audit
import com.pawga.storage.domain.LocaleDb
import com.pawga.storage.domain.StationDb
import com.pawga.storage.domain.StationTypeDb
import com.pawga.storage.model.*
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers

@Mapper(componentModel = "spring")
interface ApiMapper {

    @Mapping(source = "name", target = "locale.name")
    fun entityToDTOv1(localeDb: LocaleDb): LocaleDTO
    @InheritInverseConfiguration
    fun dtoToEntity(localeDto: LocaleDTO): LocaleDb

    @Mapping(source = "name", target = "stationType.name")
    fun entityToDTOv1(stationTypeDb: StationTypeDb): StationTypeDTO
    @InheritInverseConfiguration
    fun dtoToEntity(stationTypeDto: StationTypeDTO): StationTypeDb

    fun entityToModel(localeDb: LocaleDb): Locale
    @InheritInverseConfiguration
    fun modelToEntity(locale: Locale): LocaleDb

    fun entityToModel(stationTypeDb: StationTypeDb): StationType
    @InheritInverseConfiguration
    fun modelToEntity(stationType: StationType): StationTypeDb

    @Mapping(source = "name", target = "station.name")
    @Mapping(source = "description", target = "station.description")
    @Mapping(source = "image", target = "station.image")
    @Mapping(source = "status", target = "station.status")
    @Mapping(source = "localeDb", target = "station.locale")
    @Mapping(source = "types", target = "station.types")
    @Mapping(source = "streams", target = "station.streams")
    fun entityToDTOv1(stationDb: StationDb): StationDTOv1
    @InheritInverseConfiguration
    fun dtoToEntity(stationDTOv1: StationDTOv1): StationDb

    @Mapping(source = "localeDb", target = "localeDto")
    fun entityToDTO(stationDb: StationDb): StationDTO
    @InheritInverseConfiguration
    fun dtoToEntity(stationDTO: StationDTO): StationDb

    fun entityToDTO(audit: Audit): AuditDTO
    @InheritInverseConfiguration
    fun dtoToEntity(auditDTO: AuditDTO): Audit

    @Mapping(source = "id", target = "id") // такие преобразования можно не указывать
    @Mapping(source = "bitrate", target = "stationBitrate.bitrate")
    @Mapping(source = "stream", target = "stationBitrate.stream")
    fun entityToDTOv1(stationBitrateDb: StationBitrateDb): StationBitrateDTO
    @InheritInverseConfiguration
    fun dtoToEntity(stationBitrateDto: StationBitrateDTO): StationBitrateDb

    companion object {
        val INSTANCE: ApiMapper = Mappers.getMapper(ApiMapper::class.java)
    }
}
