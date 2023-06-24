package com.pawga.storage.model

import com.pawga.common.model.RadioStatus

/**
 * Created by sivannikov on 19.06.2023 17:44
 */
data class StationDTO(
    val id: Long,
    val name: String,
    val description: String,
    val image: String,
    val status: RadioStatus,
    val localeDto: LocaleDTO,
    val types: MutableSet<StationTypeDTO>,
    val streams: MutableSet<StationBitrateDTO>,
    val audit: AuditDTO,
)