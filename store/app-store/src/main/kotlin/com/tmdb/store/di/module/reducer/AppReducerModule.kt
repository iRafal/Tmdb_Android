package com.tmdb.store.di.module.reducer

import com.tmdb.feature.home.reducer.HomeFeatureReducer
import com.tmdb.feature.movie.details.reducer.MovieDetailsFeatureSlice
import com.tmdb.store.AppReducer
import com.tmdb.store.AppReducerImpl
import org.koin.dsl.module

fun appReducerModule() = module {
    includes(homeFeatureReducerModule())
    factory { MovieDetailsFeatureSlice() }
    factory<AppReducer> { AppReducerImpl(get<HomeFeatureReducer>(), get<MovieDetailsFeatureSlice>()) }
}
