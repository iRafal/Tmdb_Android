package com.tmdb.feature.home.ui

import com.tmdb.feature.home.ui.data.mapping.HomeFeatureStateToUiStateMapper
import com.tmdb.feature.home.ui.data.mapping.HomeMovieSectionToActionMapper
import com.tmdb.feature.home.ui.data.model.HomeUiData
import com.tmdb.store.AppStore
import com.tmdb.store.state.AppState
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {
    private val dispatcher = StandardTestDispatcher()

    private val appStore = mock<AppStore>()
    private val homeFeatureStateToUiStateMapper = mock<HomeFeatureStateToUiStateMapper>()
    private val homeMovieSectionToActionMapper = mock<HomeMovieSectionToActionMapper>()
    private val viewModel = HomeViewModel(
        appStore,
        homeFeatureStateToUiStateMapper,
        homeMovieSectionToActionMapper
    )

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun state() {
        whenever(appStore.state).thenReturn(AppState.INITIAL)
        assertEquals(viewModel.uiState, HomeUiData.INITIAL)
    }

    @Test
    fun onReloadMovieSection() {
        whenever(appStore.state).thenReturn(AppState.INITIAL)
        viewModel.onReloadMovieSection
        // TODO
        /*
         whenever(appStore.state).thenReturn(AppState.Initial)
        val name = "Name"

        viewModel.onNameChanged(name)

        verify(appStore).dispatch(QuizNameChangedAction(name))
         */
    }
}
