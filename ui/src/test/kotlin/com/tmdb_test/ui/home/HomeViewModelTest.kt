package com.tmdb_test.ui.home

import com.tmdb_test.store.state.AppState
import com.tmdb_test.store.app.AppStore
import com.tmdb_test.ui.home.data.HomeUiData
import com.tmdb_test.ui.home.data.mapping.HomeFeatureToUiStateMapper
import com.tmdb_test.ui.home.data.mapping.HomeMovieSectionToActionMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {
    private val dispatcher = StandardTestDispatcher()

    private val appStore = mock<AppStore>()
    private val homeFeatureToUiStateMapper = mock<HomeFeatureToUiStateMapper>()
    private val homeMovieSectionToActionMapper = mock<HomeMovieSectionToActionMapper>()
    private val viewModel =
        HomeViewModel(appStore, homeFeatureToUiStateMapper, homeMovieSectionToActionMapper)

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
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
        //TODO
        /*
         whenever(appStore.state).thenReturn(AppState.Initial)
        val name = "Name"

        viewModel.onNameChanged(name)

        verify(appStore).dispatch(QuizNameChangedAction(name))
         */
    }
}