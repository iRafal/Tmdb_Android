package com.tmdb.store.di.module.reducer

import com.tmdb.feature.home.reducer.HomeFeatureReducer
import com.tmdb.feature.movie.details.reducer.MovieDetailsFeatureSlice
import com.tmdb.store.AppReducer
import com.tmdb.store.AppReducerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.koin.dsl.module

@InstallIn(SingletonComponent::class)
@Module(includes = [HomeFeatureReducerModule::class])
object AppReducerModule {

    @get:Provides
    val movieDetailsFeatureSlice = MovieDetailsFeatureSlice()

    @Provides
    fun appReducer(impl: AppReducerImpl): AppReducer = impl
}

fun appReducerModule() = module {
    homeFeatureReducerModule()

    factory() {
        MovieDetailsFeatureSlice()
    }

    factory<AppReducer>() {
        AppReducerImpl(get<HomeFeatureReducer>(), get<MovieDetailsFeatureSlice>())
    }
}
