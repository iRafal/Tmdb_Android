package com.tmdb.data.api.impl_ktor.di.module

import com.tmdb.data.api.impl_ktor.discover.DiscoverApi
import com.tmdb.data.api.impl_ktor.discover.DiscoverApiImpl
import com.tmdb.data.api.impl_ktor.genre.GenreApi
import com.tmdb.data.api.impl_ktor.genre.GenreApiImpl
import com.tmdb.data.api.impl_ktor.movie.MovieApi
import com.tmdb.data.api.impl_ktor.movie.MovieApiImpl
import com.tmdb.data.api.impl_ktor.person.PersonApi
import com.tmdb.data.api.impl_ktor.person.PersonApiImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface KtorApiModule {

    @Binds
    @Singleton
    fun discoverApi(impl: DiscoverApiImpl): DiscoverApi

    @Binds
    @Singleton
    fun genreApi(impl: GenreApiImpl): GenreApi

    @Binds
    @Singleton
    fun movieApi(impl: MovieApiImpl): MovieApi

    @Binds
    @Singleton
    fun personApi(impl: PersonApiImpl): PersonApi
}