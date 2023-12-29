package com.baris.data.repository

import com.baris.core.IResult
import com.baris.domain.model.Position
import com.baris.domain.model.Satellite
import com.baris.domain.model.SatelliteDetail
import com.baris.domain.model.SatellitePosition
import com.baris.domain.repository.SatelliteRepository
import javax.inject.Inject

/**
 * Created on 29.12.2023.
 * @author Barış Keser
 */
class SatelliteRepositoryImpl @Inject constructor(): SatelliteRepository {
    override suspend fun getSatelliteList(): IResult<List<Satellite>> {
        TODO("Not yet implemented")
    }

    override suspend fun getSatelliteDetail(id: Int): IResult<SatelliteDetail> {
        TODO("Not yet implemented")
    }

    override suspend fun search(query: String): IResult<List<Satellite>> {
        TODO("Not yet implemented")
    }

    override suspend fun getSatellitePositions(): IResult<List<SatellitePosition>> {
        TODO("Not yet implemented")
    }

    override suspend fun getSatellitePosition(id: Int): IResult<Position> {
        TODO("Not yet implemented")
    }
}