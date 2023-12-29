package com.baris.data.model

import kotlinx.serialization.SerialName

/**
 * Created on 29.12.2023.
 * @author Barış Keser
 */
data class SatelliteDetailDto(
    val id: Int,
    @SerialName("cost_per_launch")
    val costPerLaunch: Long,
    @SerialName("first_flight")
    val firstFlight: String,
    val height: Int,
    val mass: Long
)
