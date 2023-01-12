package com.tmdb_test.store.env.di.modules

import com.tmdb_test.store.env.AppEnv
import com.tmdb_test.store.env.createAppDbEnvImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object AppDbModule {

    @Provides
    @JvmStatic
    fun appDatabase(): AppEnv.Database = createAppDbEnvImpl()
}