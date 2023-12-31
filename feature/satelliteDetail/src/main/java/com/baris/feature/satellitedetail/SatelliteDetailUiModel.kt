package com.baris.feature.satellitedetail

/**
 * Created on 31.12.2023.
 * @author Barış Keser
 */

data class SatelliteDetailUiModel(
    val id: Int,
    val name: String,
    val costPerLaunch: Long,
    val firstFlight: String,
    val height: Int,
    val mass: Long,
    val posX: Double,
    val posY: Double
)