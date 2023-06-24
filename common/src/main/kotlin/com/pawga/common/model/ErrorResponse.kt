package com.pawga.common.model

/**
 * Created by sivannikov on 14.06.2023 22:10
 */
data class ErrorResponse(
    val httpStatus: Int,
    val exception: String,
    val message: String,
    val fieldErrors: List<FieldError>
)
