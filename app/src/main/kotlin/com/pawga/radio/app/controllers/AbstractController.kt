package com.pawga.radio.app.controllers

import com.pawga.common.service.Converter
import com.pawga.common.service.StorageService
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.io.UnsupportedEncodingException

abstract class AbstractController<M, K, D, S : StorageService<M, K>>(
    private val service: S,
    private val converter: Converter<M, D>
) {
    @GetMapping()
    @Throws(UnsupportedEncodingException::class)
    protected open fun findAll(): ResponseEntity<List<D>> {
        return ResponseEntity(service.findAll().stream().map { converter.toDto(it) }.filter { it != null }.map { it!! }
            .toList(), HttpStatus.OK)
    }

    @PostMapping()
    @Throws(UnsupportedEncodingException::class)
    @ApiResponse(responseCode = "201")
    protected open fun create(@RequestBody dto: D): ResponseEntity<D> {
        return try {
            val model = converter.toModel(dto) ?: throw UnsupportedEncodingException()
            val key = service.create(model)
            if (key != null) {
                val obj = service.get(key)
                ResponseEntity(converter.toDto(obj!!), HttpStatus.CREATED)
            } else {
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        } catch (_: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("{uid}")
    @Throws(UnsupportedEncodingException::class)
    protected open fun get(@Parameter(description = "id заявки") @PathVariable uid: K): ResponseEntity<D> {
        val obj = service.get(uid)
        return if (obj != null) {
            ResponseEntity(converter.toDto(obj), HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PutMapping("{uid}")
    @Throws(UnsupportedEncodingException::class)
    protected open fun update(@Parameter(description = "id заявки") @PathVariable uid: K, @RequestBody dto: D):
            ResponseEntity<D> {
        val model = converter.toModel(dto) ?: throw UnsupportedEncodingException()
        val obj = service.update(uid, model)
        return if (obj != null) {
            ResponseEntity(converter.toDto(obj), HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("{uid}")
    @Throws(UnsupportedEncodingException::class)
    @ApiResponse(responseCode = "204")
    protected open fun delete(@Parameter(description = "id заявки") @PathVariable uid: K): ResponseEntity<Any> {
        return try {
            service.delete(uid)
            ResponseEntity(HttpStatus.NO_CONTENT)
        } catch (_: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}
