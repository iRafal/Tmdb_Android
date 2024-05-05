package com.tmdb.data.local.impl.objectBox.mapping

import com.tmdb.data.db.objectBox.movie.MovieEntity
import com.tmdb.data.model.MovieDataModel
import javax.inject.Inject

interface MovieDataModelToEntityMapper {
    fun map(input: MovieDataModel): MovieEntity
}

class MovieDataModelToEntityMapperImpl @Inject constructor() : MovieDataModelToEntityMapper {
    override fun map(input: MovieDataModel): MovieEntity {
        return MovieEntity(
            movieId = input.id,
            title = input.title,
            voteAverage = input.voteAverage,
            releaseDate = input.releaseDate,
            posterUrl = input.posterUrl
        )
    }
}
