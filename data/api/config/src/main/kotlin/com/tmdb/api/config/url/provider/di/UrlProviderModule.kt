package com.tmdb.api.config.url.provider.di

import com.tmdb.api.config.url.provider.base.BaseUrlProvider
import com.tmdb.api.config.url.provider.discover.DiscoverUrlProvider
import com.tmdb.api.config.url.provider.discover.DiscoverUrlProviderImpl
import com.tmdb.api.config.url.provider.genre.GenreUrlProvider
import com.tmdb.api.config.url.provider.genre.GenreUrlProviderImpl
import com.tmdb.api.config.url.provider.movie.MovieUrlProvider
import com.tmdb.api.config.url.provider.movie.MovieUrlProviderImpl
import com.tmdb.api.config.url.provider.person.PersonUrlProvider
import com.tmdb.api.config.url.provider.person.PersonUrlProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@[Module InstallIn(SingletonComponent::class)]
object UrlProviderModule {

    @[Provides Singleton]
    fun discoverUrlProvider(baseUrlProvider: BaseUrlProvider): DiscoverUrlProvider =
        DiscoverUrlProviderImpl(baseUrlProvider.discoverApiUrl)

    @[Provides Singleton]
    fun genreUrlProvider(baseUrlProvider: BaseUrlProvider): GenreUrlProvider =
        GenreUrlProviderImpl(baseUrlProvider.genreApiUrl)

    @[Provides Singleton]
    fun movieUrlProvider(baseUrlProvider: BaseUrlProvider): MovieUrlProvider =
        MovieUrlProviderImpl(baseUrlProvider.movieApiUrl)

    @[Provides Singleton]
    fun personUrlProvider(baseUrlProvider: BaseUrlProvider): PersonUrlProvider =
        PersonUrlProviderImpl(baseUrlProvider.personApiUrl)
}
