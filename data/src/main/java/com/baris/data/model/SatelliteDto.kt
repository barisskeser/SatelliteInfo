package com.baris.data.model

import kotlinx.serialization.Serializable

/**
 * Created on 29.12.2023.
 * @author Barış Keser
 */
@Serializable
data class SatelliteDto(
    val id: Int,
    val active: Boolean,
    val name: String
)
