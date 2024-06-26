package com.tmdb.feature.home.ui

import android.content.res.Resources
import androidx.compose.ui.test.assertAll
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.filter
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.tmdb.feature.home.ui.compose.assertHasNoLongClickAction
import com.tmdb.feature.home.ui.data.model.HomeMovieSectionType
import com.tmdb.feature.home.ui.data.model.HomeUiData
import com.tmdb.feature.home.ui.data.model.Movie
import com.tmdb.feature.home.ui.data.model.MovieGroup
import com.tmdb.feature.home.ui.data.model.MovieGroup.Error.NetworkError
import com.tmdb.feature.home.ui.di.TestAppComponentStore
import com.tmdb.ui.core.compose.ComposeTestTags
import javax.inject.Inject
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDate
import org.junit.Rule

class HomeScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Inject
    lateinit var resources: Resources

    @BeforeTest
    fun init() {
        TestAppComponentStore.component.inject(this)
    }

    @Test
    fun homeScreenLoadingState(): Unit = with(composeTestRule) {
        val data = HomeUiData.INITIAL

        setContent {
            HomeScreenUi(data, onEvent = {})
        }

        listOf(
            R.string.now_playing,
            R.string.popular,
            R.string.top_rated,
            R.string.upcoming
        ).forEach {
            onNodeWithText(resources.getString(it), useUnmergedTree = true)
                .assertIsDisplayed()
                .assertHasNoClickAction()
        }

        onAllNodes(matcher = hasTestTag(ComposeTestTags.TAG_CIRCULAR_PROGRESS_INDICATOR)).assertCountEquals(4)
    }

    @Test
    fun homeScreenErrorState(): Unit = with(composeTestRule) {
        val data = HomeUiData(
            isFullReload = false,
            listOf(
                MovieGroup(
                    title = R.string.popular,
                    type = HomeMovieSectionType.POPULAR,
                    movies = emptyList(),
                    isLoading = false,
                    error = MovieGroup.Error.OtherError
                ),
                MovieGroup(
                    title = R.string.top_rated,
                    type = HomeMovieSectionType.TOP_RATED,
                    movies = emptyList(),
                    isLoading = false,
                    error = MovieGroup.Error.OtherError
                ),
                MovieGroup(
                    title = R.string.now_playing,
                    type = HomeMovieSectionType.NOW_PLAYING,
                    movies = emptyList(),
                    isLoading = false,
                    error = MovieGroup.Error.OtherError
                ),
                MovieGroup(
                    title = R.string.upcoming,
                    type = HomeMovieSectionType.UPCOMING,
                    movies = emptyList(),
                    isLoading = false,
                    error = MovieGroup.Error.OtherError
                )
            )
        )

        setContent {
            HomeScreenUi(data, onEvent = {})
        }

        listOf(
            R.string.now_playing,
            R.string.popular,
            R.string.top_rated,
            R.string.upcoming
        ).forEach {
            onNodeWithText(resources.getString(it), useUnmergedTree = true)
                .assertIsDisplayed()
                .assertHasNoClickAction()
        }

        onAllNodesWithText(resources.getString(R.string.load_fail)).assertCountEquals(4)
        onAllNodesWithText(resources.getString(R.string.reload)).assertCountEquals(4).assertAll(hasClickAction())
    }

    @Test
    fun homeScreenNetworkErrorState(): Unit = with(composeTestRule) {
        val data = HomeUiData(
            isFullReload = false,
            listOf(
                MovieGroup(
                    title = R.string.popular,
                    type = HomeMovieSectionType.POPULAR,
                    movies = emptyList(),
                    isLoading = false,
                    error = NetworkError
                ),
                MovieGroup(
                    title = R.string.top_rated,
                    type = HomeMovieSectionType.TOP_RATED,
                    movies = emptyList(),
                    isLoading = false,
                    error = NetworkError
                ),
                MovieGroup(
                    title = R.string.now_playing,
                    type = HomeMovieSectionType.NOW_PLAYING,
                    movies = emptyList(),
                    isLoading = false,
                    error = NetworkError
                ),
                MovieGroup(
                    title = R.string.upcoming,
                    type = HomeMovieSectionType.UPCOMING,
                    movies = emptyList(),
                    isLoading = false,
                    error = NetworkError
                )
            )
        )

        setContent {
            HomeScreenUi(data, onEvent = {})
        }

        listOf(
            R.string.now_playing,
            R.string.popular,
            R.string.top_rated,
            R.string.upcoming
        ).forEach {
            onNodeWithText(resources.getString(it), useUnmergedTree = true)
                .assertIsDisplayed()
                .assertHasNoClickAction()
        }

        onAllNodesWithText(resources.getString(R.string.no_internet)).assertCountEquals(4)
        onAllNodesWithText(resources.getString(R.string.reload)).assertCountEquals(4).assertAll(hasClickAction())
    }

    @Test
    fun homeScreenSuccessState(): Unit = runTest {
        with(composeTestRule) {
            val movies = listOf(
                Movie(
                    id = 1,
                    title = "Movie 1",
                    averageVote = 70.7,
                    releaseDate = LocalDate.parse("2022-01-01"),
                    posterUrl = null
                ),
                Movie(
                    id = 2,
                    title = "Movie 2",
                    averageVote = 20.7,
                    releaseDate = LocalDate.parse("2020-01-01"),
                    posterUrl = null
                ),
                Movie(
                    id = 3,
                    title = "Movie 3",
                    averageVote = 95.7,
                    releaseDate = LocalDate.parse("2021-01-01"),
                    posterUrl = null
                )
            )
            val data = HomeUiData(
                isFullReload = false,
                listOf(
                    MovieGroup(
                        title = R.string.popular,
                        type = HomeMovieSectionType.POPULAR,
                        movies = movies,
                        isLoading = false,
                        error = null
                    ),
                    MovieGroup(
                        title = R.string.top_rated,
                        type = HomeMovieSectionType.TOP_RATED,
                        movies = movies,
                        isLoading = false,
                        error = null
                    ),
                    MovieGroup(
                        title = R.string.now_playing,
                        type = HomeMovieSectionType.NOW_PLAYING,
                        movies = movies,
                        isLoading = false,
                        error = null
                    ),
                    MovieGroup(
                        title = R.string.upcoming,
                        type = HomeMovieSectionType.UPCOMING,
                        movies = movies,
                        isLoading = false,
                        error = null
                    )
                )
            )

            var isItemClicked = false

            val onEvent: (HomeUiEvent) -> Unit = { event ->
                when (event) {
                    is HomeUiEvent.OpenMovie -> {
                        isItemClicked = true
                    }

                    HomeUiEvent.NavigateBack -> {
                    }

                    is HomeUiEvent.ReloadMovieSection -> {
                    }

                    HomeUiEvent.ReloadAll -> {
                    }
                }
            }

            setContent {
                HomeScreenUi(data, onEvent)
            }

            val allMovieSectionHeaders = onAllNodesWithTag(HomeScreenTestTags.TAG_MOVIE_SECTION_HEADER)
            listOf(
                R.string.now_playing,
                R.string.popular,
                R.string.top_rated,
                R.string.upcoming
            ).forEach {
                allMovieSectionHeaders.filter(hasText(resources.getString(it))).assertCountEquals(1)
            }

            movies.first().let { firstMovie ->
                onAllNodesWithTag(HomeScreenTestTags.TAG_MOVIE_ITEM)
                    .onFirst()
                    .assertIsDisplayed()
                    .assertHasClickAction()
                    .assertHasNoLongClickAction()
                    .performClick()
                assertTrue(isItemClicked)

                onAllNodesWithTag(HomeScreenTestTags.TAG_MOVIE_ITEM)
                    .filter(hasText(firstMovie.title))
                    .onFirst()
                    .assertIsDisplayed()

                onAllNodesWithTag(HomeScreenTestTags.TAG_MOVIE_ITEM)
                    .filter(hasText(checkNotNull(firstMovie.formattedReleaseDate)))
                    .onFirst()
                    .assertIsDisplayed()

                onAllNodesWithTag(HomeScreenTestTags.TAG_MOVIE_ITEM)
                    .filter(hasText(firstMovie.averageVote.toString()))
                    .onFirst()
                    .assertIsDisplayed()
            }
        }
    }
}
