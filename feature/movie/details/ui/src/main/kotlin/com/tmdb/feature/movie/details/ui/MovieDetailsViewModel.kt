package com.tmdb.feature.movie.details.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.tmdb.store.AppStore
import com.tmdb.store.feature.MovieDetailsFeature
import com.tmdb.store.state.MovieDetailsFeatureState
import com.tmdb.ui.core.compose.navigation.model.NavigationRoute
import com.tmdb.ui.core.util.viewModel.assisted.AssistedViewModelCreator
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class MovieDetailsViewModel(
    store: AppStore,
    private val handle: SavedStateHandle,
) : ViewModel() {

    private val movieId: String
        get() = handle.toRoute<NavigationRoute.MovieDetails>().movieId

    val state: StateFlow<MovieDetailsFeatureState> = store.stateFlow
        .map { it.movieDetailsState }
        .stateIn(viewModelScope, SharingStarted.Eagerly, MovieDetailsFeatureState.INITIAL)

    init {
        store.setFeatureScope(MovieDetailsFeature, viewModelScope)
    }

    class Creator @Inject constructor(private val store: AppStore) : AssistedViewModelCreator<MovieDetailsViewModel> {
        override fun create(handle: SavedStateHandle) = MovieDetailsViewModel(store, handle)
    }
}
