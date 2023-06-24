package com.pawga.common.model

/**
 * Created by sivannikov on 19.06.2023 17:45
 */
data class Station(
    val name: String,
    val description: String,
    val image: String,
    val status: RadioStatus,
    val locale: Locale,
    val types: MutableSet<StationType>,
    val streams: MutableSet<StationBitrate>,
)