package com.pawga.common.model

interface Keyed<T> {
    fun getKey(): T
    fun setKey(key: T): Boolean
}
