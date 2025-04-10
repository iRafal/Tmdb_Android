package com.tmdb.feature.home.ui.di.module

import com.tmdb.util.di.modules.DispatchersModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module(includes = [HomeUiDataMappingModule::class, DispatchersModule::class])
interface HomeFeatureModule

