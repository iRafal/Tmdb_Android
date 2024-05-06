package com.tmdb.feature.home.ui.data.model

import com.tmdb.util.date.DateFormatsUtil
import kotlinx.datetime.LocalDate
import kotlinx.datetime.toJavaLocalDate

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