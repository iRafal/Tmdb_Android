package com.tmdb.store.di.module.env

import com.tmdb.data.source.local.contract.MovieLocalDataSource
import com.tmdb.store.AppReducer
import com.tmdb.store.AppStore
import com.tmdb.store.env.contract.AppEnv
import com.tmdb.store.env.impl.createAppDbEnvImpl
import com.tmdb.util.di.modules.DISPATCHER_IO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.core.qualifier.named
import org.koin.dsl.module

@InstallIn(SingletonComponent::class)
@Module
object AppDbModule {

    @Provides
    fun appDatabase(movieSource: MovieLocalDataSource): AppEnv.Database = createAppDbEnvImpl(movieSource)
}

fun appDbModule() = module {
    factory<AppEnv.Database>() {
        createAppDbEnvImpl(get<MovieLocalDataSource>())
    }
}
