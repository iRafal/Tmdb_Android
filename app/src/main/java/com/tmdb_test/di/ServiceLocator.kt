package com.tmdb_test.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.tmdb_test.BuildConfig
//import com.tmdb_test.data.api.impl.genre.GenreApiImpl
import com.tmdb_test.data.api.impl_retrofit.discover.DiscoverApi
import com.tmdb_test.data.api.impl_retrofit.genre.GenreApi
import com.tmdb_test.data.api.impl_retrofit.movie.MovieApi
import com.tmdb_test.data.api.impl_retrofit.person.PersonApi
import com.tmdb_test.data.api.impl_retrofit.util.NetworkResponseAdapterFactory
import com.tmdb_test.data.api.url.provider.image.ImageUrlProvider
import com.tmdb_test.data.api.url.provider.image.ImageUrlProviderImpl
import com.tmdb_test.data.source.remote.discover.DiscoverRemoteDataSourceImpl
import com.tmdb_test.data.source.remote.genre.GenreRemoteDataSourceImpl
import com.tmdb_test.data.source.remote.movie.MovieRemoteDataSourceImpl
import com.tmdb_test.data.source.remote.person.PersonRemoteDataSourceImpl
import com.tmdb_test.feature.home.store.HomeFeatureSlice
import com.tmdb_test.store.app.appReducer
import com.tmdb_test.store.app.AppState
import com.tmdb_test.store.app.AppStore
import com.tmdb_test.store.app.AppStoreImpl
import com.tmdb_test.store.env.AppEnv
import com.tmdb_test.util.moviesApiResponseToDataStateMapper
import com.tmdb_test.util.moviesDataStateToFeatureStateMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Converter.Factory
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

object ServiceLocator {

    val json: Json
        get() = Json {
            ignoreUnknownKeys = true
            prettyPrint = true
        }

//    private var ktor: HttpClient? = null

//    private val apiLogger = object : Logger {
//        override fun log(message: String) {
//            logcat(DEBUG) { message }
//        }
//    }
//
//    private fun getKtorHttpClient(): HttpClient {
//        if (ktor != null) return checkNotNull(ktor)
//        return createKtorHttpClient(
//            apiKeyValue = BuildConfig.API_KEY,
//            logLevel = LogLevel.ALL,
//            logger = apiLogger,
//            apiErrorMapper = apiErrorMapper,
//        ).also { ktor = it }
//    }

//    private const val url = BuildConfig.API_BASE_URL

    private fun okHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        requestInterceptor: Interceptor,
        retryOnConnectionFailure: Boolean = true,
    ): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(requestInterceptor)
            .retryOnConnectionFailure(retryOnConnectionFailure)
            .build()
    }

    val okHttpClient = okHttpClient(
        loggingInterceptor(),
        requestInterceptor(apiKeyValue = BuildConfig.API_KEY)
    )

    fun loggingInterceptor(
        loggingLevel: Level = BODY,
    ): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply { level = loggingLevel }
    }

    fun requestInterceptor(apiKeyKey: String = "api_key", apiKeyValue: String): Interceptor {
        return Interceptor { chain ->
            val original: Request = chain.request()

            val url = original.url.newBuilder()
                .addQueryParameter(apiKeyKey, apiKeyValue)
                .build()

            val request: Request = original.newBuilder().url(url).build()

            chain.proceed(request)
        }
    }

    private val scalarsConverterFactory: ScalarsConverterFactory = ScalarsConverterFactory.create()

    @ExperimentalSerializationApi
    fun jsonConverterFactory(json: Json): Factory {
        val contentType = "application/json".toMediaType()
        // https://petnagy.medium.com/kotlinx-serialization-part2-d6c23f7839c4
        return json.asConverterFactory(contentType)
    }

    private val jsonConverterFactory = jsonConverterFactory(json)

    private val networkResponseFactory = NetworkResponseAdapterFactory()

    private val discoverApi: DiscoverApi = Retrofit.Builder()
        .baseUrl(BuildConfig.API_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(jsonConverterFactory)
        .addConverterFactory(scalarsConverterFactory)
        .addCallAdapterFactory(networkResponseFactory)
        .build()
        .create(DiscoverApi::class.java)

    private val genreApi: GenreApi = Retrofit.Builder()
        .baseUrl(BuildConfig.API_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(jsonConverterFactory)
        .addConverterFactory(scalarsConverterFactory)
        .addCallAdapterFactory(networkResponseFactory)
        .build()
        .create(GenreApi::class.java)

    private val movieApi: MovieApi = Retrofit.Builder()
        .baseUrl(BuildConfig.API_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(jsonConverterFactory)
        .addConverterFactory(scalarsConverterFactory)
        .addCallAdapterFactory(networkResponseFactory)
        .build()
        .create(MovieApi::class.java)

    private val personApi: PersonApi = Retrofit.Builder()
        .baseUrl(BuildConfig.API_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(jsonConverterFactory)
        .addConverterFactory(scalarsConverterFactory)
        .addCallAdapterFactory(networkResponseFactory)
        .build()
        .create(PersonApi::class.java)


    private val appNetwork by lazy {
        object : AppEnv.Network {
            override val discoverSource = DiscoverRemoteDataSourceImpl(discoverApi)
            override val genreSource = GenreRemoteDataSourceImpl(genreApi)
            override val movieSource = MovieRemoteDataSourceImpl(movieApi)
            override val personSource = PersonRemoteDataSourceImpl(personApi)
        }
    }

    private val appDatabase = object : AppEnv.Database {}

    private val appEnv = object : AppEnv {
        override val network: AppEnv.Network = appNetwork
        override val database: AppEnv.Database = appDatabase
    }

    val appStore: AppStore =
        AppStoreImpl(AppState.INITIAL, appEnv, appReducer, effectContext = Dispatchers.IO)

    val imageUrlProvider: ImageUrlProvider = ImageUrlProviderImpl(BuildConfig.API_IMAGE_URL)

    val homeFeatureSlice =
        HomeFeatureSlice(moviesApiResponseToDataStateMapper, moviesDataStateToFeatureStateMapper)
}