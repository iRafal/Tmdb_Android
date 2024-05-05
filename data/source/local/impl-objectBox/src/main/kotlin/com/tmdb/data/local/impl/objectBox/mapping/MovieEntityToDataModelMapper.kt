package com.tmdb.data.local.impl.objectBox.mapping

import com.tmdb.data.db.objectBox.movie.MovieEntity
import com.tmdb.data.model.MovieDataModel
import javax.inject.Inject

interface MovieEntityToDataModelMapper {
    fun map(input: MovieEntity): MovieDataModel
}

class MovieEntityToDataModelMapperImpl @Inject constructor() : MovieEntityToDataModelMapper {
    override fun map(input: MovieEntity): MovieDataModel {
        return MovieDataModel(
            id = input.movieId,
            title = input.title,
            voteAverage = input.voteAverage,
            releaseDate = input.releaseDate,
            posterUrl = input.posterUrl
        )
    }
}
