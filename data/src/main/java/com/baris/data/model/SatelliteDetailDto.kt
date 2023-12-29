package com.baris.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created on 29.12.2023.
 * @author Barış Keser
 */
@Serializable
data class SatelliteDetailDto(
    val id: Int,
    @SerialName("cost_per_launch")
    val costPerLaunch: Long,
    @SerialName("first_flight")
    val firstFlight: String,
    val height: Int,
    val mass: Long
)
