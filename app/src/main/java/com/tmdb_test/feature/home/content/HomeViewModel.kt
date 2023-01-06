package com.tmdb_test.feature.home.content

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdb_test.feature.home.content.mapping.HomeFeatureToUiStateMapper
import com.tmdb_test.feature.home.data.HomeUiData
import com.tmdb_test.feature.home.data.mapping.HomeMovieSectionToActionMapper
import com.tmdb_test.feature.home.store.HomeAction
import com.tmdb_test.feature.home.store.HomeFeature
import com.tmdb_test.store.app.AppState
import com.tmdb_test.store.app.AppStore
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
