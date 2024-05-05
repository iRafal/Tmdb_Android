package com.tmdb.feature.home.ui.data.model

import com.tmdb.feature.home.ui.data.model.HomeMovieSection.NOW_PLAYING
import com.tmdb.feature.home.ui.data.model.HomeMovieSection.NOW_POPULAR
import com.tmdb.feature.home.ui.data.model.HomeMovieSection.TOP_RATED
import com.tmdb.feature.home.ui.data.model.HomeMovieSection.UPCOMING
import com.tmdb.ui.core.data.UiState
import com.tmdb.ui.core.data.UiState.Loading
import com.tmdb.util.date.DateFormatsUtil
import kotlinx.datetime.LocalDate
import kotlinx.datetime.toJavaLocalDate

data class HomeUiData(val movieSections: Map<HomeMovieSection, UiState<List<Movie>>>) {
    data class Movie(
        val id: Int,
        val title: String,
        val averageVote: Double,
        val releaseDate: LocalDate?,
        val posterUrl: String?
    ) {
        val formattedReleaseDate: String?
            get() {
                return releaseDate?.let {
                    DateFormatsUtil.dayShortMonthNameFullYearDateFormat.format(it.toJavaLocalDate())
                }
            }
    }

    companion object {
        val INITIAL = HomeUiData(
            movieSections = mapOf(
                NOW_PLAYING to Loading(),
                NOW_POPULAR to Loading(),
                TOP_RATED to Loading(),
                UPCOMING to Loading()
            )
        )
    }
}
