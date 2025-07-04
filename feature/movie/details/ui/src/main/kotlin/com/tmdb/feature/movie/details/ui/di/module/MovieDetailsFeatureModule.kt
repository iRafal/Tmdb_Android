package com.tmdb.feature.movie.details.ui.di.module

import com.tmdb.feature.movie.details.ui.MovieDetailsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

fun movieDetailsFeatureModule() = module {
//    viewModelOf(::MovieDetailsViewModel)
    viewModel { (movieId: String) ->  MovieDetailsViewModel(get(), movieId) }
}
