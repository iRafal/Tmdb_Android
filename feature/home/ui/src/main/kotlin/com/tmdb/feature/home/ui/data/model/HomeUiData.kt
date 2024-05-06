package com.tmdb.feature.home.ui.data.model

data class HomeUiData(val isFullReload: Boolean = false, val movieGroups: List<MovieGroup> = emptyList()) {
    companion object {
        val INITIAL = HomeUiData()
    }
}
