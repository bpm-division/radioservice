package com.pawga.common.model

import jakarta.validation.constraints.Size

/**
 * Created by sivannikov on 14.06.2023 22:01
 */
data class StationType(@Size(max = 255) val name: String)