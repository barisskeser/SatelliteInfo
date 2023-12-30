package com.baris.data.datasource

import com.baris.data.room.SatellitesDatabase
import com.baris.data.room.entity.SatelliteDetailEntity
import javax.inject.Inject

/**
 * Created on 30.12.2023.
 * @author Barış Keser
 */
class LocalDataSourceImpl @Inject constructor(
    private val satellitesDatabase: SatellitesDatabase
) : LocalDataSource {
    override suspend fun getSatelliteDetail(id: Int): SatelliteDetailEntity? {
        return satellitesDatabase.getSatelliteDao().getSatelliteDetail(id)
    }

    override suspend fun saveSatelliteDetail(satelliteDetailEntity: SatelliteDetailEntity) {
        return satellitesDatabase.getSatelliteDao().saveSatelliteDetail(satelliteDetailEntity)
    }
}