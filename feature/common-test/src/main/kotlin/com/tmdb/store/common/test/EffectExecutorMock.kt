package com.tmdb.store.common.test

import com.tmdb.data.source.local.contract.MovieLocalDataSource
import com.tmdb.data.source.remote.contract.discover.DiscoverRemoteDataSource
import com.tmdb.data.source.remote.contract.genre.GenreRemoteDataSource
import com.tmdb.data.source.remote.contract.movie.MovieRemoteDataSource
import com.tmdb.data.source.remote.contract.person.PersonRemoteDataSource
import com.tmdb.store.base.Effect
import com.tmdb.store.base.feature.Feature
import com.tmdb.store.env.contract.AppEnv
import kotlinx.coroutines.runBlocking
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

interface EffectExecutorMock : Effect.Executor<AppEnv> {
    val effectExecutorScope: Effect.Scope<AppEnv>
}

fun createMockEffectExecutor(
    discoverRemoteSource: DiscoverRemoteDataSource,
    genreRemoteSource: GenreRemoteDataSource,
    movieRemoteSource: MovieRemoteDataSource,
    personRemoteSource: PersonRemoteDataSource,
    movieLocalSource: MovieLocalDataSource
): EffectExecutorMock {
    val database: AppEnv.Database = mock<AppEnv.Database>().apply {
        whenever(this.movieSource).thenReturn(movieLocalSource)
    }

    val network: AppEnv.Network = mock<AppEnv.Network>().apply {
        whenever(this.discoverSource).thenReturn(discoverRemoteSource)
        whenever(this.genreSource).thenReturn(genreRemoteSource)
        whenever(this.movieSource).thenReturn(movieRemoteSource)
        whenever(this.personSource).thenReturn(personRemoteSource)
    }

    val appEnv: AppEnv = mock<AppEnv>().apply {
        whenever(this.database).thenReturn(database)
        whenever(this.network).thenReturn(network)
    }

    return object : EffectExecutorMock {
        override val effectExecutorScope: Effect.Scope<AppEnv> =
            mock<Effect.Scope<AppEnv>>().apply {
                whenever(this.env).thenReturn(appEnv)
            }

        override fun execute(
            feature: Feature,
            effectBlock: suspend Effect.Scope<AppEnv>.() -> Unit
        ) {
            runBlocking {
                effectBlock(effectExecutorScope)
            }
        }
    }
}
