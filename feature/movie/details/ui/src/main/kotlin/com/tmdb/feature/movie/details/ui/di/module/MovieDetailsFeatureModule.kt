package com.tmdb.feature.movie.details.ui.di.module

import androidx.lifecycle.SavedStateHandle
import com.tmdb.feature.movie.details.ui.MovieDetailsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

fun movieDetailsFeatureModule() = module {
    viewModel { (handle: SavedStateHandle) ->  MovieDetailsViewModel(get(), handle) }
}
