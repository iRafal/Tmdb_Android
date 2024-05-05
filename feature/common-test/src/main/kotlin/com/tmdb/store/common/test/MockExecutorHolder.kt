package com.tmdb.store.common.test

import com.tmdb.data.source.local.contract.MovieLocalDataSource
import com.tmdb.data.source.remote.contract.discover.DiscoverRemoteDataSource
import com.tmdb.data.source.remote.contract.genre.GenreRemoteDataSource
import com.tmdb.data.source.remote.contract.movie.MovieRemoteDataSource
import com.tmdb.data.source.remote.contract.person.PersonRemoteDataSource
import org.mockito.kotlin.mock

class MockExecutorHolder {
    val discoverRemoteSource = mock<DiscoverRemoteDataSource>()
    val genreRemoteSource = mock<GenreRemoteDataSource>()
    val movieRemoteSource = mock<MovieRemoteDataSource>()
    val personRemoteSource = mock<PersonRemoteDataSource>()
    val movieLocalSource = mock<MovieLocalDataSource>()

    val executor = createMockEffectExecutor(
        discoverRemoteSource,
        genreRemoteSource,
        movieRemoteSource,
        personRemoteSource,
        movieLocalSource
    )
}
