package com.baris.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.baris.data.room.entity.SatelliteDetailEntity

/**
 * Created on 30.12.2023.
 * @author Barış Keser
 */
@Database(
    entities = [SatelliteDetailEntity::class],
    version = 1
)
abstract class SatellitesDatabase: RoomDatabase() {
    companion object {
        const val NAME = "satellites.db"
    }
    abstract fun getSatelliteDao(): SatelliteDao
}