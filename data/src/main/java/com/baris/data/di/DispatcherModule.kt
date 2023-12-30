package com.baris.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

/**
 * Created on 30.12.2023.
 * @author Barış Keser
 */

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @IoDispatcher
    @Provides
    fun provideDispatchersIO() = Dispatchers.IO

}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class IoDispatcher