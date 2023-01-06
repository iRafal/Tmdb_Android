package com.tmdb_test.api.impl_retrofit.di.module

import com.tmdb_test.api.config.url.provider.BaseUrlProvider
import com.tmdb_test.api.impl_retrofit.di.module.util.ApiFactoriesModule.ConverterFactoryJson
import com.tmdb_test.api.impl_retrofit.di.module.util.ApiFactoriesModule.ConverterFactoryScalars
import com.tmdb_test.api.impl_retrofit.di.module.util.ApiHttpClientModule.OkHttpClientRetrofit
import com.tmdb_test.api.impl_retrofit.discover.DiscoverApi
import com.tmdb_test.api.impl_retrofit.genre.GenreApi
import com.tmdb_test.api.impl_retrofit.movie.MovieApi
import com.tmdb_test.api.impl_retrofit.person.PersonApi
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
class ApiModule {

    @Provides
    fun discoverApi(
        baseUrlProvider: BaseUrlProvider,
        @OkHttpClientRetrofit client: OkHttpClient,
        @ConverterFactoryJson jsonConverterFactory: Converter.Factory,
        @ConverterFactoryScalars scalarsConverterFactory: Converter.Factory,
        factory: CallAdapter.Factory
    ): DiscoverApi {
        return api(
            url = baseUrlProvider.discoverApiUrl,
            client = client ,
            jsonConverterFactory = jsonConverterFactory,
            scalarsConverterFactory = scalarsConverterFactory,
            factory = factory,
            apiClass = DiscoverApi::class.java,
        )
    }

    @Provides
    fun genreApi(
        baseUrlProvider: BaseUrlProvider,
        @OkHttpClientRetrofit client: OkHttpClient,
        @ConverterFactoryJson jsonConverterFactory: Converter.Factory,
        @ConverterFactoryScalars scalarsConverterFactory: Converter.Factory,
        factory: CallAdapter.Factory
    ): GenreApi {
        return api(
            url = baseUrlProvider.genreApiUrl,
            client = client ,
            jsonConverterFactory = jsonConverterFactory,
            scalarsConverterFactory = scalarsConverterFactory,
            factory = factory,
            apiClass = GenreApi::class.java,
        )
    }

    @Provides
    fun movieApi(
        baseUrlProvider: BaseUrlProvider,
        @OkHttpClientRetrofit client: OkHttpClient,
        @ConverterFactoryJson jsonConverterFactory: Converter.Factory,
        @ConverterFactoryScalars scalarsConverterFactory: Converter.Factory,
        factory: CallAdapter.Factory
    ): MovieApi {
        return api(
            url = baseUrlProvider.movieApiUrl,
            client = client ,
            jsonConverterFactory = jsonConverterFactory,
            scalarsConverterFactory = scalarsConverterFactory,
            factory = factory,
            apiClass = MovieApi::class.java,
        )
    }

    @Provides
    fun personApi(
        baseUrlProvider: BaseUrlProvider,
        @OkHttpClientRetrofit client: OkHttpClient,
        @ConverterFactoryJson jsonConverterFactory: Converter.Factory,
        @ConverterFactoryScalars scalarsConverterFactory: Converter.Factory,
        factory: CallAdapter.Factory
    ): PersonApi {
        return api(
            url = baseUrlProvider.personApiUrl,
            client = client ,
            jsonConverterFactory = jsonConverterFactory,
            scalarsConverterFactory = scalarsConverterFactory,
            factory = factory,
            apiClass = PersonApi::class.java,
        )
    }

    private fun <T> api(
        url: String,
        client: OkHttpClient,
        jsonConverterFactory: Converter.Factory,
        scalarsConverterFactory: Converter.Factory,
        factory: CallAdapter.Factory,
        apiClass: Class<T>
    ): T {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(jsonConverterFactory)
            .addConverterFactory(scalarsConverterFactory)
            .addCallAdapterFactory(factory)
            .build()
            .create(apiClass)
    }
}