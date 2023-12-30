package com.baris.domain.repository

import com.baris.domain.model.Satellite
import com.baris.domain.model.SatelliteDetail
import com.baris.domain.model.SatellitePosition

/**
 * Created on 29.12.2023.
 * @author Barış Keser
 */
interface SatelliteRepository {

    suspend fun getSatelliteList(): List<Satellite>

    suspend fun getSatelliteDetail(id: Int): SatelliteDetail?

    suspend fun search(query: String): List<Satellite>

    suspend fun getSatellitePosition(id: Int): SatellitePosition?

}