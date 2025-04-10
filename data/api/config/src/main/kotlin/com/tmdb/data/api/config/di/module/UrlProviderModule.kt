package com.tmdb.data.api.config.di.module

import com.tmdb.data.api.config.url.provider.base.BaseUrlProvider
import com.tmdb.data.api.config.url.provider.discover.DiscoverUrlProvider
import com.tmdb.data.api.config.url.provider.discover.DiscoverUrlProviderImpl
import com.tmdb.data.api.config.url.provider.genre.GenreUrlProvider
import com.tmdb.data.api.config.url.provider.genre.GenreUrlProviderImpl
import com.tmdb.data.api.config.url.provider.movie.MovieUrlProvider
import com.tmdb.data.api.config.url.provider.movie.MovieUrlProviderImpl
import com.tmdb.data.api.config.url.provider.person.PersonUrlProvider
import com.tmdb.data.api.config.url.provider.person.PersonUrlProviderImpl
import dagger.Module
import dagger.Provides

@Module(includes = [BaseUrlProviderModule::class])
object UrlProviderModule {

    @Provides
    fun discoverUrlProvider(baseUrlProvider: BaseUrlProvider): DiscoverUrlProvider =
        DiscoverUrlProviderImpl(baseUrlProvider.discoverApiUrl)

    @Provides
    fun genreUrlProvider(baseUrlProvider: BaseUrlProvider): GenreUrlProvider =
        GenreUrlProviderImpl(baseUrlProvider.genreApiUrl)

    @Provides
    fun movieUrlProvider(baseUrlProvider: BaseUrlProvider): MovieUrlProvider =
        MovieUrlProviderImpl(baseUrlProvider.movieApiUrl)

    @Provides
    fun personUrlProvider(baseUrlProvider: BaseUrlProvider): PersonUrlProvider =
        PersonUrlProviderImpl(baseUrlProvider.personApiUrl)
}
