package com.tmdb.data.source.local.impl.realm.mapping

import com.tmdb.data.db.realm.movie.MovieEntity
import com.tmdb.data.model.MovieDataModel
import javax.inject.Inject

interface MovieEntityToDataModelMapper {
    fun map(input: MovieEntity): MovieDataModel
}

class MovieEntityToDataModelMapperImpl @Inject constructor() : MovieEntityToDataModelMapper {
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
