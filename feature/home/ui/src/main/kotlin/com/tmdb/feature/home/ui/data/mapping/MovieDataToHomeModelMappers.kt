package com.tmdb.feature.home.ui.data.mapping

import com.tmdb.data.model.MovieDataModel
import com.tmdb.feature.home.ui.data.model.HomeUiData
import javax.inject.Inject

interface MovieDataToHomeModelMapper {
    fun map(input: MovieDataModel): HomeUiData.Movie
}

class MovieDataToHomeModelMapperImpl @Inject constructor() : MovieDataToHomeModelMapper {
    override fun map(input: MovieDataModel): HomeUiData.Movie {
        return HomeUiData.Movie(
            id = checkNotNull(input.id),
            title = checkNotNull(input.title),
            averageVote = input.voteAverage ?: 0.0,
            releaseDate = input.releaseDate,
            posterUrl = input.posterUrl
        )
    }
}

interface MovieDataItemsToHomeModelMapper {
    fun map(input: List<MovieDataModel>): List<HomeUiData.Movie>
}

class MovieDataItemsToHomeModelMapperImpl @Inject constructor(
    private val movieDataToHomeModelMapper: MovieDataToHomeModelMapper
) : MovieDataItemsToHomeModelMapper {
    override fun map(input: List<MovieDataModel>): List<HomeUiData.Movie> {
        return input.map { movieDataToHomeModelMapper.map(it) }
    }
}

