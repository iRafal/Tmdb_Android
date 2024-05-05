package com.tmdb.di.component.app.module

import androidx.lifecycle.ViewModelProvider
import com.tmdb.ui.core.di.module.ImageLoadingModule
import com.tmdb.ui.core.util.viewModel.MultiViewModelFactory
import dagger.Binds
import dagger.Module

@Module(includes = [ImageLoadingModule::class])
interface UiModule {
    @Binds
    fun viewModelFactory(impl: MultiViewModelFactory): ViewModelProvider.Factory
}
