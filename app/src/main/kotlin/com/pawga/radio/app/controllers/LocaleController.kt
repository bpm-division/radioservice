package com.pawga.radio.app.controllers

import com.pawga.storage.domain.LocaleDb
import com.pawga.storage.mapper.EmptyConverter
import com.pawga.storage.model.LocaleDTO
import com.pawga.storage.service.DbStorageService
import com.pawga.storage.service.LocaleDbStorageService
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
    value = ["/api/locales"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
@Tag(name = "Языки станций", description = "Работа с языками станций")
class LocaleController(
    service: LocaleDbStorageService,
    converter: EmptyConverter<LocaleDTO>
) : AbstractController<LocaleDTO, Long, LocaleDTO, DbStorageService<LocaleDTO, LocaleDb, Long>>
    (service, converter) {
    @Operation(summary = "Получить все языки") // просто пример документирования через override одного метода
    override fun findAll(): ResponseEntity<List<LocaleDTO>> {
        return super.findAll()
    }
}
