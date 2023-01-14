package com.tmdb_test.api.config.di.module.url.provider

import com.tmdb_test.api.config.url.provider.BaseUrlProvider
import com.tmdb_test.api.config.url.provider.BaseUrlProviderImpl
import com.tmdb_test.api.config.url.provider.discover.DiscoverUrlProvider
import com.tmdb_test.api.config.url.provider.discover.DiscoverUrlProviderImpl
import com.tmdb_test.api.config.url.provider.genre.GenreUrlProvider
import com.tmdb_test.api.config.url.provider.genre.GenreUrlProviderImpl
import com.tmdb_test.api.config.url.provider.movie.MovieUrlProvider
import com.tmdb_test.api.config.url.provider.movie.MovieUrlProviderImpl
import com.tmdb_test.api.config.url.provider.person.PersonUrlProvider
import com.tmdb_test.api.config.url.provider.person.PersonUrlProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UrlProviderModule {
    @Provides
    @Singleton
    fun baseUrlProvider(impl: BaseUrlProviderImpl): BaseUrlProvider = impl

    @Provides
    @Singleton
    fun discoverUrlProvider(baseUrlProvider: BaseUrlProvider): DiscoverUrlProvider =
        DiscoverUrlProviderImpl(baseUrlProvider.discoverApiUrl)

    @Provides
    @Singleton
    fun genreUrlProvider(baseUrlProvider: BaseUrlProvider): GenreUrlProvider =
        GenreUrlProviderImpl(baseUrlProvider.genreApiUrl)

    @Provides
    @Singleton
    fun movieUrlProvider(baseUrlProvider: BaseUrlProvider): MovieUrlProvider =
        MovieUrlProviderImpl(baseUrlProvider.movieApiUrl)

    @Provides
    @Singleton
    fun personUrlProvider(baseUrlProvider: BaseUrlProvider): PersonUrlProvider =
        PersonUrlProviderImpl(baseUrlProvider.personApiUrl)
}