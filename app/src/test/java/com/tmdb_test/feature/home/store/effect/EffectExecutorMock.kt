package com.tmdb_test.feature.home.store.effect

import com.tmdb_test.data.source.remote.discover.DiscoverRemoteDataSource
import com.tmdb_test.data.source.remote.genre.GenreRemoteDataSource
import com.tmdb_test.data.source.remote.movie.MovieRemoteDataSource
import com.tmdb_test.data.source.remote.person.PersonRemoteDataSource
import com.tmdb_test.store.base.Effect
import com.tmdb_test.store.base.feature.Feature
import com.tmdb_test.store.env.AppEnv
import kotlinx.coroutines.runBlocking
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

interface EffectExecutorMock : Effect.Executor<AppEnv> {
    val effectExecutorScope: Effect.Executor.Scope<AppEnv>
}

fun createMockEffectExecutor(
    discoverSource: DiscoverRemoteDataSource,
    genreSource: GenreRemoteDataSource,
    movieSource: MovieRemoteDataSource,
    personSource: PersonRemoteDataSource,
): EffectExecutorMock {

    val database: AppEnv.Database = mock()

    val network: AppEnv.Network = mock<AppEnv.Network>().apply {
        whenever(this.discoverSource).thenReturn(discoverSource)
        whenever(this.genreSource).thenReturn(genreSource)
        whenever(this.movieSource).thenReturn(movieSource)
        whenever(this.personSource).thenReturn(personSource)
    }

    val appEnv: AppEnv = mock<AppEnv>().apply {
        whenever(this.database).thenReturn(database)
        whenever(this.network).thenReturn(network)
    }

    return object : EffectExecutorMock {
        override val effectExecutorScope: Effect.Executor.Scope<AppEnv> =
            mock<Effect.Executor.Scope<AppEnv>>().apply {
                whenever(this.env).thenReturn(appEnv)
            }

        override fun execute(
            feature: Feature,
            effectBlock: suspend Effect.Executor.Scope<AppEnv>.() -> Unit
        ) {
            runBlocking {
                effectBlock(effectExecutorScope)
            }
        }
    }
}