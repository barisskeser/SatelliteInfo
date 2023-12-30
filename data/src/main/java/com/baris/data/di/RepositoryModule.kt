package com.baris.data.di

import com.baris.data.repository.SatelliteRepositoryImpl
import com.baris.domain.repository.SatelliteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created on 29.12.2023.
 * @author Barış Keser
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindSatelliteRepository(satelliteRepositoryImpl: SatelliteRepositoryImpl): SatelliteRepository

}