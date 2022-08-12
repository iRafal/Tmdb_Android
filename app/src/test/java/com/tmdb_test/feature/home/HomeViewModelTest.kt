package com.tmdb_test.feature.home

import com.tmdb_test.feature.home.content.HomeViewModel
import com.tmdb_test.feature.home.data.HomeUiData
import com.tmdb_test.store.app.AppState
import com.tmdb_test.store.app.AppStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {
    private val dispatcher = StandardTestDispatcher()

    private val appStore = mock<AppStore>()
    private val viewModel = HomeViewModel(appStore)

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
        Assert.assertEquals(viewModel.uiState, HomeUiData.INITIAL)
    }

    @Test
    fun onReloadMovieSection() {
        whenever(appStore.state).thenReturn(AppState.INITIAL)
        viewModel.onReloadMovieSection
        /*
         whenever(appStore.state).thenReturn(AppState.Initial)
        val name = "Name"

        viewModel.onNameChanged(name)

        verify(appStore).dispatch(QuizNameChangedAction(name))
         */


    }
}