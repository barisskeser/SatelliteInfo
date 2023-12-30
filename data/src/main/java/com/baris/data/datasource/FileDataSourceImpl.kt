package com.baris.data.datasource

import android.content.res.AssetManager
import com.baris.data.model.PositionListDto
import com.baris.data.model.SatelliteDetailDto
import com.baris.data.model.SatelliteDto
import com.baris.data.model.SatellitePositionDto
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.InputStream
import javax.inject.Inject

/**
 * Created on 30.12.2023.
 * @author Barış Keser
 */
class FileDataSourceImpl @Inject constructor(
    private val assetManager: AssetManager,
    private val json: Json
) : FileDataSource {

    companion object {
        const val SATELLITE_LIST = "satellite-list.json"
        const val SATELLITE_DETAIL = "satellite-detail.json"
        const val POSITIONS = "positions.json"
    }

    override suspend fun getSatelliteList(): List<SatelliteDto> {
        return assetManager.open(SATELLITE_LIST).use(json::decodeFromStream)
    }

    override suspend fun getSatelliteDetail(id: Int): SatelliteDetailDto? {
        return assetManager.open(SATELLITE_DETAIL)
            .use<InputStream, List<SatelliteDetailDto>>(json::decodeFromStream).find {
                it.id == id
            }
    }

    override suspend fun search(query: String): List<SatelliteDto> {
        return assetManager.open(SATELLITE_LIST)
            .use<InputStream, List<SatelliteDto>>(json::decodeFromStream).filter {
                it.name.lowercase().contains(query.lowercase())
            }
    }

    override suspend fun getSatellitePositions(id: Int): SatellitePositionDto? {
        return assetManager.open(POSITIONS)
            .use<InputStream, PositionListDto>(json::decodeFromStream).list.find {
                it.id == id
            }
    }
}