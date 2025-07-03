package com.tmdb.data.source.local.impl.realm.mapping

import com.tmdb.data.db.realm.movie.MovieEntity
import com.tmdb.data.model.MovieDataModel

fun interface MovieDataModelToEntityMapper {
    fun map(input: MovieDataModel): MovieEntity
}

class MovieDataModelToEntityMapperImpl : MovieDataModelToEntityMapper {
    override fun map(input: MovieDataModel): MovieEntity {
        return MovieEntity(
            id = input.id,
            title = input.title,
            voteAverage = input.voteAverage,
            posterUrl = input.posterUrl
        ).apply {
            releaseDate = input.releaseDate
        }
    }
}
