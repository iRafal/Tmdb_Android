package com.tmdb.data.api.implKtor.di.module

import com.tmdb.data.api.config.di.module.ApiConfigModule
import com.tmdb.data.api.config.di.module.apiConfigModule
import com.tmdb.data.api.config.url.provider.base.BaseUrlProvider
import com.tmdb.data.api.config.url.provider.base.BaseUrlProviderImpl
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
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import org.koin.dsl.module

@InstallIn(SingletonComponent::class)
@Module(includes = [ApiUtilModule::class, ApiConfigModule::class])
interface ApiModule {
    @Binds
    fun discoverApi(impl: DiscoverApiImpl): DiscoverApi

    @Binds
    fun genreApi(impl: GenreApiImpl): GenreApi

    @Binds
    fun movieApi(impl: MovieApiImpl): MovieApi

    @Binds
    fun personApi(impl: PersonApiImpl): PersonApi
}

fun apiModule() = module {
    apiUtilModule()
    apiConfigModule()
    factory<DiscoverApi> { DiscoverApiImpl(get<HttpClient>(), get<DiscoverUrlProvider>()) }
    factory<GenreApi> { GenreApiImpl(get<HttpClient>(), get<GenreUrlProvider>()) }
    factory<MovieApi> { MovieApiImpl(get<HttpClient>(), get<MovieUrlProvider>()) }
    factory<PersonApi> { PersonApiImpl(get<HttpClient>(), get<PersonUrlProvider>()) }
}
