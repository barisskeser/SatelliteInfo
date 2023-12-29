package com.baris.data.di

import com.baris.data.datasource.FileDataSource
import com.baris.data.datasource.FileDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created on 30.12.2023.
 * @author Barış Keser
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindFileDataSource(fileDataSourceImpl: FileDataSourceImpl): FileDataSource

}