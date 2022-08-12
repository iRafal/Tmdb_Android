package com.tmdb_test.feature.movie.details.content

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdb_test.di.ServiceLocator
import com.tmdb_test.feature.movie.details.store.MovieDetailsFeature
import com.tmdb_test.feature.movie.details.store.MovieDetailsFeatureState
import com.tmdb_test.store.app.AppStore
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class MovieDetailsViewModel(private val store: AppStore) : ViewModel() {

    val state: StateFlow<MovieDetailsFeatureState> = store.stateFlow
        .map { it.movieDetailsState }
        .stateIn(viewModelScope, SharingStarted.Eagerly, MovieDetailsFeatureState.INITIAL)

    init {
        store.setFeatureScope(MovieDetailsFeature, viewModelScope)
    }
}