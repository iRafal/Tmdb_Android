package com.tmdb.feature.home.ui.di.module

import com.tmdb.feature.home.ui.HomeViewModel
import com.tmdb.util.di.modules.DISPATCHER_IO
import com.tmdb.util.di.modules.dispatchersModule
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun homeFeatureModule() = module {
    includes(homeUiDataMappingModule(), dispatchersModule())
    viewModel { HomeViewModel(get(), get(named(DISPATCHER_IO)), get(), get()) }
}
