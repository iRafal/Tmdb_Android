package com.tmdb_test.data.api.impl_ktor.di.module

import com.tmdb_test.api.config.BuildConfig
import com.tmdb_test.api.config.url.provider.BaseUrlProvider
import com.tmdb_test.data.api.impl_ktor.di.ApiErrorMapper
import com.tmdb_test.data.api.impl_ktor.di.apiErrorMapper
import com.tmdb_test.data.api.impl_ktor.di.createKtorHttpClient
import com.tmdb_test.data.api.impl_ktor.discover.DiscoverApi
import com.tmdb_test.data.api.impl_ktor.discover.DiscoverApiImpl
import com.tmdb_test.data.api.impl_ktor.genre.GenreApi
import com.tmdb_test.data.api.impl_ktor.genre.GenreApiImpl
import com.tmdb_test.data.api.impl_ktor.movie.MovieApi
import com.tmdb_test.data.api.impl_ktor.movie.MovieApiImpl
import com.tmdb_test.data.api.impl_ktor.person.PersonApi
import com.tmdb_test.data.api.impl_ktor.person.PersonApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import kotlinx.serialization.json.Json


@Module
@InstallIn(SingletonComponent::class)
class KtorApiModule {

    @Provides
    fun discoverApi(
        ktorHttpClient: HttpClient,
        baseUrlProvider: BaseUrlProvider,
    ): DiscoverApi = DiscoverApiImpl(
        client = ktorHttpClient,
        baseUrl = baseUrlProvider.discoverApiUrl,
    )

    @Provides
    fun genreApi(
        ktorHttpClient: HttpClient,
        baseUrlProvider: BaseUrlProvider,
    ): GenreApi = GenreApiImpl(
        client = ktorHttpClient,
        baseUrl = baseUrlProvider.genreApiUrl,
    )

    @Provides
    fun movieApi(
        ktorHttpClient: HttpClient,
        baseUrlProvider: BaseUrlProvider,
    ): MovieApi = MovieApiImpl(
        client = ktorHttpClient,
        baseUrl = baseUrlProvider.movieApiUrl,
    )

    @Provides
    fun personApi(
        ktorHttpClient: HttpClient,
        baseUrlProvider: BaseUrlProvider,
    ): PersonApi = PersonApiImpl(
        client = ktorHttpClient,
        baseUrl = baseUrlProvider.personApiUrl,
    )

    @Provides
    fun getKtorHttpClient(json: Json, apiErrorMapper: ApiErrorMapper): HttpClient =
        createKtorHttpClient(
            apiKey = BuildConfig.API_KEY,
            logLevel = LogLevel.BODY,
            apiErrorMapper = apiErrorMapper,
            logger = Logger.DEFAULT,
            json = json,
        )

    @Provides
    fun apiErrorMapper(): ApiErrorMapper = apiErrorMapper

    @Provides
    fun json() = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
    }
}