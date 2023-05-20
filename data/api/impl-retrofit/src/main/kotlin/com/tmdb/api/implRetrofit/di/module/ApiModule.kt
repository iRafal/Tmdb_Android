package com.tmdb.api.implRetrofit.di.module

import com.tmdb.api.config.url.provider.base.BaseUrlProvider
import com.tmdb.api.implRetrofit.di.module.util.ApiFactoriesModule.ConverterFactoryJson
import com.tmdb.api.implRetrofit.di.module.util.ApiFactoriesModule.ConverterFactoryScalars
import com.tmdb.api.implRetrofit.di.module.util.ApiHttpClientModule.OkHttpClientRetrofit
import com.tmdb.api.implRetrofit.discover.DiscoverApi
import com.tmdb.api.implRetrofit.genre.GenreApi
import com.tmdb.api.implRetrofit.movie.MovieApi
import com.tmdb.api.implRetrofit.person.PersonApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun discoverApi(
        baseUrlProvider: BaseUrlProvider,
        @OkHttpClientRetrofit client: OkHttpClient,
        @ConverterFactoryJson jsonConverterFactory: Converter.Factory,
        @ConverterFactoryScalars scalarsConverterFactory: Converter.Factory,
        factory: CallAdapter.Factory
    ): DiscoverApi = api(
        url = baseUrlProvider.discoverApiUrl,
        client = client,
        jsonConverterFactory = jsonConverterFactory,
        scalarsConverterFactory = scalarsConverterFactory,
        factory = factory,
        apiClass = DiscoverApi::class.java
    )

    @Provides
    fun genreApi(
        baseUrlProvider: BaseUrlProvider,
        @OkHttpClientRetrofit client: OkHttpClient,
        @ConverterFactoryJson jsonConverterFactory: Converter.Factory,
        @ConverterFactoryScalars scalarsConverterFactory: Converter.Factory,
        factory: CallAdapter.Factory
    ): GenreApi = api(
        url = baseUrlProvider.genreApiUrl,
        client = client,
        jsonConverterFactory = jsonConverterFactory,
        scalarsConverterFactory = scalarsConverterFactory,
        factory = factory,
        apiClass = GenreApi::class.java
    )

    @Provides
    fun movieApi(
        baseUrlProvider: BaseUrlProvider,
        @OkHttpClientRetrofit client: OkHttpClient,
        @ConverterFactoryJson jsonConverterFactory: Converter.Factory,
        @ConverterFactoryScalars scalarsConverterFactory: Converter.Factory,
        factory: CallAdapter.Factory
    ): MovieApi = api(
        url = baseUrlProvider.movieApiUrl,
        client = client,
        jsonConverterFactory = jsonConverterFactory,
        scalarsConverterFactory = scalarsConverterFactory,
        factory = factory,
        apiClass = MovieApi::class.java
    )

    @Provides
    fun personApi(
        baseUrlProvider: BaseUrlProvider,
        @OkHttpClientRetrofit client: OkHttpClient,
        @ConverterFactoryJson jsonConverterFactory: Converter.Factory,
        @ConverterFactoryScalars scalarsConverterFactory: Converter.Factory,
        factory: CallAdapter.Factory
    ): PersonApi = api(
        url = baseUrlProvider.personApiUrl,
        client = client,
        jsonConverterFactory = jsonConverterFactory,
        scalarsConverterFactory = scalarsConverterFactory,
        factory = factory,
        apiClass = PersonApi::class.java
    )

    private fun <T> api(
        url: String,
        client: OkHttpClient,
        jsonConverterFactory: Converter.Factory,
        scalarsConverterFactory: Converter.Factory,
        factory: CallAdapter.Factory,
        apiClass: Class<T>
    ): T = Retrofit.Builder()
        .baseUrl(url)
        .client(client)
        .addConverterFactory(jsonConverterFactory)
        .addConverterFactory(scalarsConverterFactory)
        .addCallAdapterFactory(factory)
        .build()
        .create(apiClass)
}