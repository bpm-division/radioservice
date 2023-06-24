package com.pawga.storage.repository

import com.pawga.storage.domain.LocaleDb
import org.springframework.data.jpa.repository.JpaRepository

interface LocaleRepository : JpaRepository<LocaleDb, Long> {

    fun existsByNameIgnoreCase(name: String?): Boolean
    fun findByName(name: String): LocaleDb?

}