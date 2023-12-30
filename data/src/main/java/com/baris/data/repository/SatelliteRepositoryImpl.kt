package com.baris.data.repository

import com.baris.data.datasource.FileDataSource
import com.baris.data.datasource.LocalDataSource
import com.baris.data.mapper.toDomain
import com.baris.data.mapper.toEntity
import com.baris.domain.model.Satellite
import com.baris.domain.model.SatelliteDetail
import com.baris.domain.model.SatellitePosition
import com.baris.domain.repository.SatelliteRepository
import javax.inject.Inject

/**
 * Created on 29.12.2023.
 * @author Barış Keser
 */
class SatelliteRepositoryImpl @Inject constructor(
    private val fileDataSource: FileDataSource,
    private val localDataSource: LocalDataSource
) : SatelliteRepository {
    override suspend fun getSatelliteList(): List<Satellite> {
        return fileDataSource.getSatelliteList().map { it.toDomain() }
    }

    override suspend fun getSatelliteDetail(id: Int): SatelliteDetail? {
        return localDataSource.getSatelliteDetail(id)?.toDomain() ?: kotlin.run {
            fileDataSource.getSatelliteDetail(id)?.also {
                localDataSource.saveSatelliteDetail(it.toEntity())
            }?.toDomain()
        }
    }

    override suspend fun search(query: String): List<Satellite> {
        return fileDataSource.search(query).map { it.toDomain() }
    }

    override suspend fun getSatellitePosition(id: Int): SatellitePosition? {
        return fileDataSource.getSatellitePositions(id)?.toDomain()
    }

}