package com.tmdb.data.source.local.implRoom.mapping

import com.tmdb.data.db.room.movie.MovieEntity
import com.tmdb.data.model.MovieDataModel

fun interface MovieEntityToDataModelMapper {
    fun map(input: MovieEntity): MovieDataModel
}

class MovieEntityToDataModelMapperImpl : MovieEntityToDataModelMapper {
    override fun map(input: MovieEntity): MovieDataModel {
        return MovieDataModel(
            id = input.id,
            title = input.title,
            voteAverage = input.voteAverage,
            releaseDate = input.releaseDate,
            posterUrl = input.posterUrl
        )
    }
}
