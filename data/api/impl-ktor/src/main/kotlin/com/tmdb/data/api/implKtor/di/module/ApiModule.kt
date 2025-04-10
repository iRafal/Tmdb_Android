package com.tmdb.data.api.implKtor.di.module

import com.tmdb.data.api.config.di.module.ApiConfigModule
import com.tmdb.data.api.implKtor.discover.DiscoverApi
import com.tmdb.data.api.implKtor.discover.DiscoverApiImpl
import com.tmdb.data.api.implKtor.genre.GenreApi
import com.tmdb.data.api.implKtor.genre.GenreApiImpl
import com.tmdb.data.api.implKtor.movie.MovieApi
import com.tmdb.data.api.implKtor.movie.MovieApiImpl
import com.tmdb.data.api.implKtor.person.PersonApi
import com.tmdb.data.api.implKtor.person.PersonApiImpl
import com.tmdb.util.di.qualifiers.ApplicationScope
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module(includes = [ApiUtilModule::class, ApiConfigModule::class])
interface ApiModule {

    @[Binds ApplicationScope]
    fun discoverApi(impl: DiscoverApiImpl): DiscoverApi

    @[Binds ApplicationScope]
    fun genreApi(impl: GenreApiImpl): GenreApi

    @[Binds ApplicationScope]
    fun movieApi(impl: MovieApiImpl): MovieApi

    @[Binds ApplicationScope]
    fun personApi(impl: PersonApiImpl): PersonApi
}
