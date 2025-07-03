package com.tmdb.feature.home.ui.di.module

import com.tmdb.feature.home.ui.data.mapping.HomeFeatureStateToUiStateMapper
import com.tmdb.feature.home.ui.data.mapping.HomeFeatureStateToUiStateMapperImpl
import com.tmdb.feature.home.ui.data.mapping.HomeMovieGroupToActionMapper
import com.tmdb.feature.home.ui.data.mapping.HomeMovieGroupToActionMapperImpl
import com.tmdb.feature.home.ui.data.mapping.MovieDataToHomeModelMapper
import com.tmdb.feature.home.ui.data.mapping.MovieDataToHomeModelMapperImpl
import com.tmdb.util.di.modules.DispatchersModule
import com.tmdb.util.di.modules.dispatchersModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.koin.dsl.module

@InstallIn(SingletonComponent::class)
@Module(includes = [HomeUiDataMappingModule::class, DispatchersModule::class])
interface HomeFeatureModule

fun homeFeatureModule() = module {
    homeUiDataMappingModule()
    dispatchersModule()
}
