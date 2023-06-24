package com.pawga.radio.app.controllers

import com.pawga.storage.model.StationDTO
import com.pawga.storage.domain.StationDb
import com.pawga.storage.mapper.EmptyConverter
import com.pawga.storage.service.DbStorageService
import com.pawga.storage.service.StationDbStorageService
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
    value = ["/api/stations"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
@Tag(name = "Станции", description = "Работа со станциями")
class StationsController(
    service: StationDbStorageService,
    converter: EmptyConverter<StationDTO>
) : AbstractController<StationDTO, Long, StationDTO, DbStorageService<StationDTO, StationDb, Long>>
    (service, converter) {
    @Operation(summary = "Получить все станции")
    override fun findAll(): ResponseEntity<List<StationDTO>> {
        return super.findAll()
    }
}
