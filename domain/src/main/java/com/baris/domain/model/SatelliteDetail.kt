package com.baris.domain.model

/**
 * Created on 29.12.2023.
 * @author Barış Keser
 */
data class SatelliteDetail(
    val id: Int,
    val costPerLaunch: Long,
    val firstFlight: String,
    val height: Int,
    val mass: Long
)
