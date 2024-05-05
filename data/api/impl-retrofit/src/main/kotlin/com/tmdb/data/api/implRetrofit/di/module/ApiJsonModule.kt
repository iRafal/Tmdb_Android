package com.tmdb.data.api.implRetrofit.di.module

import com.tmdb.data.api.implRetrofit.di.ApiDependenciesProvider
import dagger.Module
import dagger.Provides


@Module
object ApiJsonModule {

    @Provides
    fun json() = ApiDependenciesProvider.json()
}
