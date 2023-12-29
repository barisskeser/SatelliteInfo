package com.baris.domain.repository

import com.baris.core.IResult
import com.baris.domain.model.Position
import com.baris.domain.model.SatelliteDetail
import com.baris.domain.model.Satellite
import com.baris.domain.model.SatellitePosition

/**
 * Created on 29.12.2023.
 * @author Barış Keser
 */
interface SatelliteRepository {

    suspend fun getSatelliteList(): IResult<List<Satellite>>

    suspend fun getSatelliteDetail(id: Int): IResult<SatelliteDetail>

    suspend fun search(query: String): IResult<List<Satellite>>

    suspend fun getSatellitePositions(): IResult<List<SatellitePosition>>

    suspend fun getSatellitePosition(id: Int): IResult<Position>

}