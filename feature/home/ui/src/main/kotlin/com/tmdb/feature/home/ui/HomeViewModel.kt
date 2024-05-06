package com.tmdb.feature.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdb.feature.home.ui.data.mapping.HomeFeatureStateToUiStateMapper
import com.tmdb.feature.home.ui.data.mapping.HomeMovieGroupToActionMapper
import com.tmdb.feature.home.ui.data.model.HomeMovieSectionType
import com.tmdb.feature.home.ui.data.model.HomeUiData
import com.tmdb.store.AppStore
import com.tmdb.store.action.HomeAction
import com.tmdb.store.feature.HomeFeature
import com.tmdb.store.state.AppState
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel @Inject constructor(
    private val store: AppStore,
    private val homeFeatureStateToUiStateMapper: HomeFeatureStateToUiStateMapper,
    private val homeMovieGroupToActionMapper: HomeMovieGroupToActionMapper
) : ViewModel() {

    val uiState: HomeUiData
        get() = uiStateFlow.value

    private val state: StateFlow<AppState> = store.stateFlow

    val uiStateFlow: StateFlow<HomeUiData> = state
        .map { appState -> homeFeatureStateToUiStateMapper.map(appState.homeState) }
        .flowOn(Dispatchers.IO)
        .stateIn(viewModelScope, SharingStarted.Eagerly, HomeUiData.INITIAL)

    val onReloadMovieGroup: (HomeMovieSectionType) -> Unit = { movieSection ->
        store.dispatch(homeMovieGroupToActionMapper.map(movieSection))
    }

    val onReloadAll: () -> Unit = {
        store.dispatch(HomeAction.ReloadAllMovies)
    }

    init {
        store.setFeatureScope(HomeFeature, viewModelScope)
        store.dispatch(HomeAction.LoadMovieSections)
    }

    override fun onCleared() {
        super.onCleared()
        store.dispatch(HomeAction.ResetState)
    }
}
