package com.tmdb_test.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdb_test.store.app.AppStore
import com.tmdb_test.store.feature.details.MovieDetailsFeature
import com.tmdb_test.store.state.movie.details.MovieDetailsFeatureState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    store: AppStore
) : ViewModel() {

    val state: StateFlow<MovieDetailsFeatureState> = store.stateFlow
        .map { it.movieDetailsState }
        .stateIn(viewModelScope, SharingStarted.Eagerly, MovieDetailsFeatureState.INITIAL)

    init {
        store.setFeatureScope(MovieDetailsFeature, viewModelScope)
    }
}