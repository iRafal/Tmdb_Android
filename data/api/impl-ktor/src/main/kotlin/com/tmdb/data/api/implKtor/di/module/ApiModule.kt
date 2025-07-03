package com.tmdb.data.api.implKtor.di.module

import com.tmdb.data.api.config.di.module.apiConfigModule
import com.tmdb.data.api.config.url.provider.discover.DiscoverUrlProvider
import com.tmdb.data.api.config.url.provider.genre.GenreUrlProvider
import com.tmdb.data.api.config.url.provider.movie.MovieUrlProvider
import com.tmdb.data.api.config.url.provider.person.PersonUrlProvider
import com.tmdb.data.api.implKtor.discover.DiscoverApi
import com.tmdb.data.api.implKtor.discover.DiscoverApiImpl
import com.tmdb.data.api.implKtor.genre.GenreApi
import com.tmdb.data.api.implKtor.genre.GenreApiImpl
import com.tmdb.data.api.implKtor.movie.MovieApi
import com.tmdb.data.api.implKtor.movie.MovieApiImpl
import com.tmdb.data.api.implKtor.person.PersonApi
import com.tmdb.data.api.implKtor.person.PersonApiImpl
import io.ktor.client.HttpClient
import org.koin.dsl.module

fun apiModule() = module {
    includes(apiUtilModule(), apiConfigModule())
    factory<DiscoverApi> { DiscoverApiImpl(get<HttpClient>(), get<DiscoverUrlProvider>()) }
    factory<GenreApi> { GenreApiImpl(get<HttpClient>(), get<GenreUrlProvider>()) }
    factory<MovieApi> { MovieApiImpl(get<HttpClient>(), get<MovieUrlProvider>()) }
    factory<PersonApi> { PersonApiImpl(get<HttpClient>(), get<PersonUrlProvider>()) }
}
