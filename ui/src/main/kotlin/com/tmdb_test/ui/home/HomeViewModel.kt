package com.tmdb_test.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdb_test.store.action.home.HomeAction
import com.tmdb_test.store.state.AppState
import com.tmdb_test.store.app.AppStore
import com.tmdb_test.store.feature.home.HomeFeature
import com.tmdb_test.ui.home.data.HomeMovieSection
import com.tmdb_test.ui.home.data.HomeUiData
import com.tmdb_test.ui.home.data.mapping.HomeFeatureToUiStateMapper
import com.tmdb_test.ui.home.data.mapping.HomeMovieSectionToActionMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val store: AppStore,
    private val homeFeatureToUiStateMapper: @JvmSuppressWildcards HomeFeatureToUiStateMapper,
    private val homeMovieSectionToActionMapper: @JvmSuppressWildcards HomeMovieSectionToActionMapper,
) : ViewModel() {

    var uiState: HomeUiData = HomeUiData.INITIAL
        get() = uiStateFlow.value

    private val state: StateFlow<AppState> = store.stateFlow

    val uiStateFlow: StateFlow<HomeUiData> = state
        .map { appState -> homeFeatureToUiStateMapper(appState.homeState) }
        .flowOn(Dispatchers.IO)
        .stateIn(viewModelScope, SharingStarted.Eagerly, HomeUiData.INITIAL)

    val onReloadMovieSection: (HomeMovieSection) -> Unit = { movieSection ->
        store.dispatch(homeMovieSectionToActionMapper(movieSection))
    }

    init {
        store.setFeatureScope(HomeFeature, viewModelScope)
        store.dispatch(HomeAction.LoadMovieSections)
    }
}
