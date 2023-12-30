package com.baris.data.datasource

import com.baris.data.room.entity.SatelliteDetailEntity

/**
 * Created on 30.12.2023.
 * @author Barış Keser
 */
interface LocalDataSource {
    suspend fun getSatelliteDetail(id: Int): SatelliteDetailEntity?

    suspend fun saveSatelliteDetail(satelliteDetailEntity: SatelliteDetailEntity)
}