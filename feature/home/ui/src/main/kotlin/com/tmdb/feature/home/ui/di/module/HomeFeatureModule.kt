package com.tmdb.feature.home.ui.di.module

import androidx.lifecycle.ViewModel
import com.tmdb.feature.home.ui.HomeViewModel
import com.tmdb.ui.core.di.qualifiers.ViewModelKey
import com.tmdb.util.di.modules.DispatchersModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [HomeUiDataMappingModule::class, DispatchersModule::class])
interface HomeFeatureModule {
    @[Binds IntoMap ViewModelKey(HomeViewModel::class)]
    fun homeViewModel(homeViewModel: HomeViewModel): ViewModel
}
