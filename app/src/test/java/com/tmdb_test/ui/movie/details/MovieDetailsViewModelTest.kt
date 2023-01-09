package com.tmdb_test.ui.movie.details

import com.tmdb_test.store.app.AppStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.mockito.kotlin.mock

//TODO
@OptIn(ExperimentalCoroutinesApi::class)
class MovieDetailsViewModelTest {
    private val dispatcher = StandardTestDispatcher()

    private val appStore = mock<AppStore>()
    private val viewModel = MovieDetailsViewModel(appStore)

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}