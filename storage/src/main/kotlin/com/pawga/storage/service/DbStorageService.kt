package com.pawga.storage.service

import com.pawga.common.model.Keyed
import com.pawga.common.service.Converter
import com.pawga.common.service.StorageService
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

/**
 * Created by sivannikov on 18.06.2023 12:12
 */

/**
 * E - тип Entity, хранимый в БД, должен реализовывать интерфейс Keyed
 * K - тип ключа
 * D - тип записи для обмена с БД
 */

open class DbStorageService<D, E : Keyed<K>, K : Any>(
    private val repository: JpaRepository<E, K>,
    private val converter: Converter<D, E>
) : StorageService<D, K> {

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    override fun findAll(): List<D> {
        return repository.findAll().stream().map { record -> converter.toModel(record) }.filter { it != null }
            .map { it!! }.toList()
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    override fun get(uid: K): D? {
        val record = repository.findById(uid)
        return if (record.isPresent) {
            converter.toModel(record.get())
        } else {
            null
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun create(obj: D): K? {
        val record = converter.toDto(obj) ?: return null
        val result = repository.save(record)
        return result.getKey()
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun update(uid: K, obj: D): D? {
        val isPresent = repository.findById(uid).isPresent
        if (!isPresent) return null
        val record = converter.toDto(obj) ?: return null
        repository.save(record)
        return converter.toModel(record)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun delete(uid: K): Boolean {
        val exist = repository.existsById(uid)
        return if (exist) {
            repository.deleteById(uid)
            true
        } else {
            false
        }
    }
}