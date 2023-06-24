package com.pawga.radio.app.controllers

import com.pawga.storage.model.StationBitrateDTO
import com.pawga.radio_bootify.domain.StationBitrateDb
import com.pawga.storage.mapper.EmptyConverter
import com.pawga.storage.service.DbStorageService
import com.pawga.storage.service.StationBitrateDbStorageService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@RestController
@RequestMapping(
    value = ["/api/bitrates"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
@Tag(name = "Битрейты станций", description = "Работа с типами станций")
class StationBitrateController(
    service: StationBitrateDbStorageService,
    converter: EmptyConverter<StationBitrateDTO>
) : AbstractController<StationBitrateDTO, Long, StationBitrateDTO, DbStorageService<StationBitrateDTO, StationBitrateDb, Long>>
    (service, converter) {
    @Operation(summary = "Получить все типы станций")
    override fun findAll(): ResponseEntity<List<StationBitrateDTO>> {
        return super.findAll()
    }
}
