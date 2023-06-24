package com.pawga.storage.model

import java.time.LocalDateTime

/**
 * Created by sivannikov on 23.06.2023 19:44
 */
data class AuditDTO(val createdOn: LocalDateTime, val updatedOn: LocalDateTime)