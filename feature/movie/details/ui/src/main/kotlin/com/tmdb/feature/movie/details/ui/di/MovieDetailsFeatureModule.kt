package com.tmdb.feature.movie.details.ui.di

import androidx.lifecycle.ViewModel
import com.tmdb.feature.movie.details.ui.MovieDetailsViewModel
import com.tmdb.ui.core.di.qualifiers.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
interface MovieDetailsFeatureModule {
    @[Binds IntoMap ViewModelKey(MovieDetailsViewModel::class)]
    fun movieDetailsViewModel(movieDetailsViewModel: MovieDetailsViewModel): ViewModel
}
