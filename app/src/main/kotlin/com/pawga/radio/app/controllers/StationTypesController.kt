package com.pawga.radio.app.controllers

import com.pawga.storage.domain.StationTypeDb
import com.pawga.storage.service.DbStorageService
import com.pawga.storage.mapper.EmptyConverter
import com.pawga.storage.model.StationTypeDTO
import com.pawga.storage.service.StationTypeDbStorageService
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
    value = ["/api/types"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
@Tag(name = "Типы станций", description = "Работа с типами станций")
class StationTypesController(
    service: StationTypeDbStorageService,
    converter: EmptyConverter<StationTypeDTO>
) : AbstractController<StationTypeDTO, Long, StationTypeDTO, DbStorageService<StationTypeDTO, StationTypeDb, Long>>
    (service, converter) {
    @Operation(summary = "Получить все типы станций")
    override fun findAll(): ResponseEntity<List<StationTypeDTO>> {
        return super.findAll()
    }
}
