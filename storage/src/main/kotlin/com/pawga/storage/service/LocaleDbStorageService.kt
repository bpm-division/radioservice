package com.pawga.storage.service

import com.pawga.storage.domain.LocaleDb
import com.pawga.storage.model.LocaleDTO
import com.pawga.storage.mapper.LocaleDtoConverter
import com.pawga.storage.repository.LocaleRepository
import org.springframework.stereotype.Service

/**
 * Created by sivannikov on 18.06.2023 20:28
 */
@Service
class LocaleDbStorageService(
    repository: LocaleRepository,
    converter: LocaleDtoConverter
):  DbStorageService<LocaleDTO, LocaleDb, Long>(repository, converter)