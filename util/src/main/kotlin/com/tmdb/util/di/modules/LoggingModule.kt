package com.tmdb.util.di.modules

import com.tmdb.util.logging.impl.AppLoggerImpl
import com.tmdb.util.logging.AppLogger
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface LoggingModule {
    @Binds
    fun logger(impl: AppLoggerImpl): AppLogger
}
