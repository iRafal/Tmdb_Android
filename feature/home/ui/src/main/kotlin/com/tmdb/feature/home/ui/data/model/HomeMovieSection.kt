package com.tmdb.feature.home.ui.data.model

import androidx.annotation.StringRes
import com.tmdb.feature.home.ui.R

enum class HomeMovieSection(@StringRes val labelRes: Int) {
    NOW_PLAYING(R.string.now_playing),
    NOW_POPULAR(R.string.now_popular),
    TOP_RATED(R.string.top_rated),
    UPCOMING(R.string.upcoming)
}
