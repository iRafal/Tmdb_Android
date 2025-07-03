package com.tmdb.feature.movie.details.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdb.store.AppStore
import com.tmdb.store.feature.MovieDetailsFeature
import com.tmdb.store.state.MovieDetailsFeatureState
import com.tmdb.ui.core.util.viewModel.assisted.AssistedViewModelGenericCreator
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class MovieDetailsViewModelNav3(
    store: AppStore,
    private val movieId: String,
) : ViewModel() {
    val state: StateFlow<MovieDetailsFeatureState> = store.stateFlow
        .map { it.movieDetailsState }
        .stateIn(viewModelScope, SharingStarted.Eagerly, MovieDetailsFeatureState.INITIAL)

    init {
        store.setFeatureScope(MovieDetailsFeature, viewModelScope)
    }

    class Creator @Inject constructor(private val store: AppStore) :
        AssistedViewModelGenericCreator<String, MovieDetailsViewModelNav3> {
        override fun create(input: String) = MovieDetailsViewModelNav3(store, input)
    }
}
