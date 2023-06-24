package com.pawga.common.service

/**
 * M - тип модели
 * K - тип "ключа"
 */
interface StorageService<M, K> {
    fun findAll(): List<M>
    fun create(obj: M): K?
    fun get(uid: K): M?
    fun update(uid: K, obj: M): M?
    fun delete(uid: K): Boolean
}
