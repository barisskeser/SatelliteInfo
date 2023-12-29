package com.baris.data.mapper

import com.baris.data.model.PositionDto
import com.baris.data.model.SatelliteDetailDto
import com.baris.data.model.SatelliteDto
import com.baris.data.model.SatellitePositionDto
import com.baris.domain.model.Position
import com.baris.domain.model.Satellite
import com.baris.domain.model.SatelliteDetail
import com.baris.domain.model.SatellitePosition

/**
 * Created on 29.12.2023.
 * @author Barış Keser
 */

fun PositionDto.toDomain(): Position {
    return Position(
        posX = this.posX,
        posY = this.posY
    )
}

fun SatelliteDetailDto.toDomain(): SatelliteDetail {
    return SatelliteDetail(
        id = this.id,
        costPerLaunch = this.costPerLaunch,
        firstFlight = this.firstFlight,
        height = this.height,
        mass = this.mass
    )
}

fun SatelliteDto.toDomain(): Satellite {
    return Satellite(
        id = this.id,
        isActive = this.active,
        name = this.name
    )
}

fun SatellitePositionDto.toDomain(): SatellitePosition {
    return SatellitePosition(
        id = this.id,
        positions = this.positions.map { it.toDomain() }
    )
}