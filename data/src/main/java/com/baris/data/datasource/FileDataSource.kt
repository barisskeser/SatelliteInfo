package com.baris.data.datasource

import com.baris.data.model.SatelliteDetailDto
import com.baris.data.model.SatelliteDto
import com.baris.data.model.SatellitePositionDto

/**
 * Created on 29.12.2023.
 * @author Barış Keser
 */
interface FileDataSource {
    suspend fun getSatelliteList(): List<SatelliteDto>

    suspend fun getSatelliteDetail(id: Int): SatelliteDetailDto?

    suspend fun search(query: String): List<SatelliteDto>

    suspend fun getSatellitePositions(id: Int): SatellitePositionDto?
}