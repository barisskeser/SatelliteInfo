package com.baris.data.model

/**
 * Created on 29.12.2023.
 * @author Barış Keser
 */
data class SatelliteDto(
    val id: Int,
    val costPerLaunch: Long,
    val firstFlight: String,
    val height: Int,
    val mass: Long
)
