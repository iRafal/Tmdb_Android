package com.tmdb.data.api.implRetrofit.di.module

import com.tmdb.data.api.config.di.module.apiConfigModule
import com.tmdb.data.api.config.di.module.urlProviderModule
import com.tmdb.data.api.config.url.provider.base.BaseUrlProvider
import com.tmdb.data.api.implRetrofit.di.ApiDependenciesProvider
import com.tmdb.data.api.implRetrofit.di.module.ApiHttpClientModule.apiHttpClientModule
import com.tmdb.data.api.implRetrofit.discover.DiscoverApi
import com.tmdb.data.api.implRetrofit.genre.GenreApi
import com.tmdb.data.api.implRetrofit.movie.MovieApi
import com.tmdb.data.api.implRetrofit.person.PersonApi
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter

fun apiModule() = module {
    includes(
        apiFactoriesModule(),
        apiHttpClientModule(),
        apiJsonModule(),
        urlProviderModule(),
        apiConfigModule()
    )

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

