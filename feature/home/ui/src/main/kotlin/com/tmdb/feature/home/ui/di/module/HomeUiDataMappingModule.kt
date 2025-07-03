package com.tmdb.feature.home.ui.di.module

import com.tmdb.feature.home.ui.data.mapping.HomeFeatureStateToUiStateMapper
import com.tmdb.feature.home.ui.data.mapping.HomeFeatureStateToUiStateMapperImpl
import com.tmdb.feature.home.ui.data.mapping.HomeMovieGroupToActionMapper
import com.tmdb.feature.home.ui.data.mapping.HomeMovieGroupToActionMapperImpl
import com.tmdb.feature.home.ui.data.mapping.MovieDataToHomeModelMapper
import com.tmdb.feature.home.ui.data.mapping.MovieDataToHomeModelMapperImpl
import com.tmdb.store.env.contract.AppEnv
import com.tmdb.store.env.impl.createAppDbEnvImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.koin.dsl.module

@InstallIn(SingletonComponent::class)
@Module
interface HomeUiDataMappingModule {
    @Binds
    fun homeFeatureToUiStateMapper(impl: HomeFeatureStateToUiStateMapperImpl): HomeFeatureStateToUiStateMapper

    @Binds
    fun movieDataToHomeModelMapper(impl: MovieDataToHomeModelMapperImpl): MovieDataToHomeModelMapper

    @Binds
    fun homeMovieGroupToActionMapper(impl: HomeMovieGroupToActionMapperImpl): HomeMovieGroupToActionMapper
}

fun homeUiDataMappingModule() = module {
    factory<HomeFeatureStateToUiStateMapper>() {
        HomeFeatureStateToUiStateMapperImpl(get<MovieDataToHomeModelMapper>())
    }

    factory<MovieDataToHomeModelMapper>() {
        MovieDataToHomeModelMapperImpl()
    }

    factory<HomeMovieGroupToActionMapper>() {
        HomeMovieGroupToActionMapperImpl()
    }
}
