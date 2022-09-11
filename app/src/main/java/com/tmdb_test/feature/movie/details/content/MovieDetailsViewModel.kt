package com.tmdb_test.feature.movie.details.content

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdb_test.feature.movie.details.store.MovieDetailsFeature
import com.tmdb_test.feature.movie.details.store.MovieDetailsFeatureState
import com.tmdb_test.store.app.AppStore
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