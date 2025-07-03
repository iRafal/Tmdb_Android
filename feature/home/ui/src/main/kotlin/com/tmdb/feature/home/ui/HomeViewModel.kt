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
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(
    private val store: AppStore,
    dispatcherIo: CoroutineDispatcher,
    private val homeFeatureStateToUiStateMapper: HomeFeatureStateToUiStateMapper,
    private val homeMovieGroupToActionMapper: HomeMovieGroupToActionMapper
) : ViewModel() {
    val uiState: HomeUiData
        get() = uiStateFlow.value

    private val state: StateFlow<AppState> = store.stateFlow

    val uiStateFlow: StateFlow<HomeUiData> = state
        .map { appState -> homeFeatureStateToUiStateMapper.map(appState.homeState) }
        .flowOn(dispatcherIo)
        .stateIn(viewModelScope, SharingStarted.Eagerly, HomeUiData.INITIAL)

    fun reloadMovieGroup(movieSection: HomeMovieSectionType) {
        store.dispatch(homeMovieGroupToActionMapper.map(movieSection))
    }

    fun reloadAllMovies() {
        store.dispatch(HomeAction.ReloadAllMovies)
    }

    init {
        with(store) {
            setFeatureScope(HomeFeature, viewModelScope)
            dispatch(HomeAction.LoadMovieSections)
        }
    }

    override fun onCleared() {
        super.onCleared()
        store.dispatch(HomeAction.ResetState)
    }
}
