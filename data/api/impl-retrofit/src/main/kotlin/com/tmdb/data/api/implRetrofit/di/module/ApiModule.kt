package com.tmdb.data.api.implRetrofit.di.module

import com.tmdb.data.api.config.di.module.ApiConfigModule
import com.tmdb.data.api.config.di.module.UrlProviderModule
import com.tmdb.data.api.config.di.module.apiConfigModule
import com.tmdb.data.api.config.di.module.urlProviderModule
import com.tmdb.data.api.config.url.provider.base.BaseUrlProvider
import com.tmdb.data.api.implRetrofit.di.ApiDependenciesProvider
import com.tmdb.data.api.implRetrofit.di.module.ApiFactoriesModule.ConverterFactoryJson
import com.tmdb.data.api.implRetrofit.di.module.ApiFactoriesModule.ConverterFactoryScalars
import com.tmdb.data.api.implRetrofit.di.module.ApiHttpClientModule.OkHttpClientRetrofit
import com.tmdb.data.api.implRetrofit.di.module.ApiHttpClientModule.apiHttpClientModule
import com.tmdb.data.api.implRetrofit.discover.DiscoverApi
import com.tmdb.data.api.implRetrofit.genre.GenreApi
import com.tmdb.data.api.implRetrofit.movie.MovieApi
import com.tmdb.data.api.implRetrofit.person.PersonApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter

@InstallIn(SingletonComponent::class)
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
    @Provides
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

    @Provides
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

    @Provides
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

    @Provides
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

fun apiModule() = module {
    apiFactoriesModule()
    apiHttpClientModule()
    apiJsonModule()
    urlProviderModule()
    apiConfigModule()

    factory<DiscoverApi> {
        ApiDependenciesProvider.api(
            url = get<BaseUrlProvider>().discoverApiUrl,
            client = get<OkHttpClient>(named("OkHttpClientRetrofit")),
            jsonConverterFactory = get<Converter.Factory>(named("ConverterFactoryJson")),
            scalarsConverterFactory = get<Converter.Factory>(named("ConverterFactoryScalars")),
            factory = get<CallAdapter.Factory>(),
            apiClass = DiscoverApi::class.java
        )
    }

    factory<GenreApi> {
        ApiDependenciesProvider.api(
            url = get<BaseUrlProvider>().genreApiUrl,
            client = get<OkHttpClient>(named("OkHttpClientRetrofit")),
            jsonConverterFactory = get<Converter.Factory>(named("ConverterFactoryJson")),
            scalarsConverterFactory = get<Converter.Factory>(named("ConverterFactoryScalars")),
            factory = get<CallAdapter.Factory>(),
            apiClass = GenreApi::class.java
        )
    }

    factory<MovieApi> {
        ApiDependenciesProvider.api(
            url = get<BaseUrlProvider>().movieApiUrl,
            client = get<OkHttpClient>(named("OkHttpClientRetrofit")),
            jsonConverterFactory = get<Converter.Factory>(named("ConverterFactoryJson")),
            scalarsConverterFactory = get<Converter.Factory>(named("ConverterFactoryScalars")),
            factory = get<CallAdapter.Factory>(),
            apiClass = MovieApi::class.java
        )
    }

    factory<PersonApi> {
        ApiDependenciesProvider.api(
            url = get<BaseUrlProvider>().personApiUrl,
            client = get<OkHttpClient>(named("OkHttpClientRetrofit")),
            jsonConverterFactory = get<Converter.Factory>(named("ConverterFactoryJson")),
            scalarsConverterFactory = get<Converter.Factory>(named("ConverterFactoryScalars")),
            factory = get<CallAdapter.Factory>(),
            apiClass = PersonApi::class.java
        )
    }
}

