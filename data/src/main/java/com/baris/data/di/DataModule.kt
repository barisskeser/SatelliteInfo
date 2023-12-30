package com.baris.data.di

import android.content.Context
import android.content.res.AssetManager
import androidx.room.Room
import com.baris.data.room.SatellitesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

/**
 * Created on 30.12.2023.
 * @author Barış Keser
 */
@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideJson() = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideAssetsManager(@ApplicationContext context: Context): AssetManager {
        return context.assets
    }

    @Provides
    @Singleton
    fun provideSatelliteDatabase(@ApplicationContext context: Context): SatellitesDatabase {
        return Room.databaseBuilder(
            context,
            SatellitesDatabase::class.java,
            SatellitesDatabase.NAME
        ).build()
    }

}