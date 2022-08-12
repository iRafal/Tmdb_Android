package com.tmdb_test.feature.home.content

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdb_test.feature.home.data.HomeUiData
import com.tmdb_test.feature.home.data.map
import com.tmdb_test.feature.home.data.mapToHomeAction
import com.tmdb_test.feature.home.store.HomeAction
import com.tmdb_test.feature.home.store.HomeFeature
import com.tmdb_test.feature.home.store.HomeFeatureState
import com.tmdb_test.store.app.AppState
import com.tmdb_test.store.app.AppStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(private val store: AppStore) : ViewModel() {

    var uiState: HomeUiData = HomeUiData.INITIAL
        get() = uiStateFlow.value

    private val state: StateFlow<AppState> = store.stateFlow

    val uiStateFlow: StateFlow<HomeUiData> = state
        .map { appState -> appState.homeState.map() }
        .flowOn(Dispatchers.IO)
        .stateIn(viewModelScope, SharingStarted.Eagerly, HomeUiData.INITIAL)

    val onReloadMovieSection: (HomeMovieSection) -> Unit = { movieSection ->
        store.dispatch(movieSection.mapToHomeAction())
    }

    init {
        store.setFeatureScope(HomeFeature, viewModelScope)
        store.dispatch(HomeAction.LoadMovieSections)
    }
}