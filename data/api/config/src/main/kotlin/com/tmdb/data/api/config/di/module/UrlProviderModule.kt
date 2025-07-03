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
import org.koin.dsl.module

fun urlProviderModule() = module {
    includes(baseUrlProviderModule())
    factory<DiscoverUrlProvider> { DiscoverUrlProviderImpl(get<BaseUrlProvider>().discoverApiUrl) }
    factory<GenreUrlProvider> { GenreUrlProviderImpl(get<BaseUrlProvider>().genreApiUrl) }
    factory<MovieUrlProvider> { MovieUrlProviderImpl(get<BaseUrlProvider>().movieApiUrl) }
    factory<PersonUrlProvider> { PersonUrlProviderImpl(get<BaseUrlProvider>().personApiUrl) }
}
