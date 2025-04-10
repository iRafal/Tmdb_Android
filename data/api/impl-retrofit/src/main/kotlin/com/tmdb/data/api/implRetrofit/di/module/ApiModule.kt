package com.tmdb.data.api.implRetrofit.di.module

import com.tmdb.data.api.config.di.module.ApiConfigModule
import com.tmdb.data.api.config.di.module.UrlProviderModule
import com.tmdb.data.api.config.url.provider.base.BaseUrlProvider
import com.tmdb.data.api.implRetrofit.di.ApiDependenciesProvider
import com.tmdb.data.api.implRetrofit.di.module.ApiFactoriesModule.ConverterFactoryJson
import com.tmdb.data.api.implRetrofit.di.module.ApiFactoriesModule.ConverterFactoryScalars
import com.tmdb.data.api.implRetrofit.di.module.ApiHttpClientModule.OkHttpClientRetrofit
import com.tmdb.data.api.implRetrofit.discover.DiscoverApi
import com.tmdb.data.api.implRetrofit.genre.GenreApi
import com.tmdb.data.api.implRetrofit.movie.MovieApi
import com.tmdb.data.api.implRetrofit.person.PersonApi
import com.tmdb.util.di.qualifiers.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter

@Module(
    includes = [
        ApiFactoriesModule::class,
        ApiHttpClientModule::class,
        ApiJsonModule::class,
        UrlProviderModule::class,
        ApiConfigModule::class,
    ]
)
object ApiModule {
    @[Provides ApplicationScope]
    fun discoverApi(
        baseUrlProvider: BaseUrlProvider,
        @OkHttpClientRetrofit client: OkHttpClient,
        @ConverterFactoryJson jsonConverterFactory: Converter.Factory,
        @ConverterFactoryScalars scalarsConverterFactory: Converter.Factory,
        factory: CallAdapter.Factory
    ): DiscoverApi = ApiDependenciesProvider.api(
        url = baseUrlProvider.discoverApiUrl,
        client = client,
        jsonConverterFactory = jsonConverterFactory,
        scalarsConverterFactory = scalarsConverterFactory,
        factory = factory,
        apiClass = DiscoverApi::class.java
    )

    @[Provides ApplicationScope]
    fun genreApi(
        baseUrlProvider: BaseUrlProvider,
        @OkHttpClientRetrofit client: OkHttpClient,
        @ConverterFactoryJson jsonConverterFactory: Converter.Factory,
        @ConverterFactoryScalars scalarsConverterFactory: Converter.Factory,
        factory: CallAdapter.Factory
    ): GenreApi = ApiDependenciesProvider.api(
        url = baseUrlProvider.genreApiUrl,
        client = client,
        jsonConverterFactory = jsonConverterFactory,
        scalarsConverterFactory = scalarsConverterFactory,
        factory = factory,
        apiClass = GenreApi::class.java
    )

    @[Provides ApplicationScope]
    fun movieApi(
        baseUrlProvider: BaseUrlProvider,
        @OkHttpClientRetrofit client: OkHttpClient,
        @ConverterFactoryJson jsonConverterFactory: Converter.Factory,
        @ConverterFactoryScalars scalarsConverterFactory: Converter.Factory,
        factory: CallAdapter.Factory
    ): MovieApi = ApiDependenciesProvider.api(
        url = baseUrlProvider.movieApiUrl,
        client = client,
        jsonConverterFactory = jsonConverterFactory,
        scalarsConverterFactory = scalarsConverterFactory,
        factory = factory,
        apiClass = MovieApi::class.java
    )

    @[Provides ApplicationScope]
    fun personApi(
        baseUrlProvider: BaseUrlProvider,
        @OkHttpClientRetrofit client: OkHttpClient,
        @ConverterFactoryJson jsonConverterFactory: Converter.Factory,
        @ConverterFactoryScalars scalarsConverterFactory: Converter.Factory,
        factory: CallAdapter.Factory
    ): PersonApi = ApiDependenciesProvider.api(
        url = baseUrlProvider.personApiUrl,
        client = client,
        jsonConverterFactory = jsonConverterFactory,
        scalarsConverterFactory = scalarsConverterFactory,
        factory = factory,
        apiClass = PersonApi::class.java
    )
}
