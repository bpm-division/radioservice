package com.pawga.common.model

import jakarta.validation.constraints.Size

/**
 * Created by sivannikov on 14.06.2023 22:09
 */
data class Locale(@Size(max = 8) val name: String)
