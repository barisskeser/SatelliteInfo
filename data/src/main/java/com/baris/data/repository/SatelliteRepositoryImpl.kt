package com.baris.data.repository

import com.baris.data.datasource.FileDataSource
import com.baris.data.datasource.LocalDataSource
import com.baris.data.di.IoDispatcher
import com.baris.data.mapper.toDomain
import com.baris.data.mapper.toEntity
import com.baris.domain.model.Satellite
import com.baris.domain.model.SatelliteDetail
import com.baris.domain.model.SatellitePosition
import com.baris.domain.repository.SatelliteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created on 29.12.2023.
 * @author Barış Keser
 */
class SatelliteRepositoryImpl @Inject constructor(
    private val fileDataSource: FileDataSource,
    private val localDataSource: LocalDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : SatelliteRepository {
    override suspend fun getSatelliteList(): List<Satellite> = withContext(dispatcher) {
        fileDataSource.getSatelliteList().map { it.toDomain() }
    }

    override suspend fun getSatelliteDetail(id: Int): SatelliteDetail? = withContext(dispatcher) {
        localDataSource.getSatelliteDetail(id)?.toDomain()
            ?: fileDataSource.getSatelliteDetail(id)?.also {
                localDataSource.saveSatelliteDetail(it.toEntity())
            }?.toDomain()

    }

    override suspend fun search(query: String): List<Satellite> = withContext(dispatcher) {
        fileDataSource.search(query).map { it.toDomain() }
    }

    override suspend fun getSatellitePosition(id: Int): SatellitePosition? =
        withContext(dispatcher) {
            fileDataSource.getSatellitePositions(id)?.toDomain()
        }

}