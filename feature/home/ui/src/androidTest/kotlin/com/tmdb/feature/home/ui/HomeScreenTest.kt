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
import com.tmdb.feature.home.ui.HomeUiEvent.NavigateBack
import com.tmdb.feature.home.ui.HomeUiEvent.OpenMovie
import com.tmdb.feature.home.ui.HomeUiEvent.ReloadMovieSection
import com.tmdb.feature.home.ui.compose.assertHasNoLongClickAction
import com.tmdb.feature.home.ui.data.model.HomeMovieSection
import com.tmdb.feature.home.ui.data.model.HomeMovieSection.NOW_PLAYING
import com.tmdb.feature.home.ui.data.model.HomeMovieSection.NOW_POPULAR
import com.tmdb.feature.home.ui.data.model.HomeMovieSection.TOP_RATED
import com.tmdb.feature.home.ui.data.model.HomeMovieSection.UPCOMING
import com.tmdb.feature.home.ui.data.model.HomeUiData
import com.tmdb.feature.home.ui.data.model.HomeUiData.Movie
import com.tmdb.ui.core.compose.ComposeTestTags
import com.tmdb.ui.core.data.UiState
import com.tmdb.ui.core.data.UiState.Success
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import javax.inject.Inject
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDate
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class HomeScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var resources: Resources

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun homeScreenLoadingState(): Unit = with(composeTestRule) {
        val data = HomeUiData.INITIAL

        setContent {
            HomeScreenUi(data, onEvent = {})
        }

        HomeMovieSection.values().forEach { section ->
            onNodeWithText(resources.getString(section.labelRes), useUnmergedTree = true)
                .assertIsDisplayed()
                .assertHasNoClickAction()
        }

        onAllNodes(matcher = hasTestTag(ComposeTestTags.TAG_CIRCULAR_PROGRESS_INDICATOR)).assertCountEquals(4)
    }

    @Test
    fun homeScreenErrorState(): Unit = with(composeTestRule) {
        val data = HomeUiData(
            mapOf(
                NOW_PLAYING to UiState.Error(),
                NOW_POPULAR to UiState.Error(),
                TOP_RATED to UiState.Error(),
                UPCOMING to UiState.Error()
            )
        )

        setContent {
            HomeScreenUi(data, onEvent = {})
        }

        HomeMovieSection.values().forEach { section ->
            onNodeWithText(resources.getString(section.labelRes), useUnmergedTree = true)
                .assertIsDisplayed()
                .assertHasNoClickAction()
        }

        onAllNodesWithText(resources.getString(R.string.load_fail)).assertCountEquals(4)
        onAllNodesWithText(resources.getString(R.string.reload)).assertCountEquals(4).assertAll(hasClickAction())
    }

    @Test
    fun homeScreenNetworkErrorState(): Unit = with(composeTestRule) {
        val data = HomeUiData(
            mapOf(
                NOW_PLAYING to UiState.NetworkError(),
                NOW_POPULAR to UiState.NetworkError(),
                TOP_RATED to UiState.NetworkError(),
                UPCOMING to UiState.NetworkError()
            )
        )

        setContent {
            HomeScreenUi(data, onEvent = {})
        }

        HomeMovieSection.values().forEach { section ->
            onNodeWithText(resources.getString(section.labelRes), useUnmergedTree = true)
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
                mapOf(
                    NOW_PLAYING to Success(movies),
                    NOW_POPULAR to Success(movies),
                    TOP_RATED to Success(movies),
                    UPCOMING to Success(movies)
                )
            )

            var isItemClicked = false

            val onEvent: (HomeUiEvent) -> Unit = { event ->
                when (event) {
                    NavigateBack -> {
                    }
                    is OpenMovie -> {
                        isItemClicked = true
                    }
                    is ReloadMovieSection -> {
                    }
                }
            }

            setContent {
                HomeScreenUi(data, onEvent)
            }

            val allMovieSectionHeaders = onAllNodesWithTag(HomeScreenTestTags.TAG_MOVIE_SECTION_HEADER)
            HomeMovieSection.values().forEach { section ->
                allMovieSectionHeaders.filter(hasText(resources.getString(section.labelRes))).assertCountEquals(1)
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
