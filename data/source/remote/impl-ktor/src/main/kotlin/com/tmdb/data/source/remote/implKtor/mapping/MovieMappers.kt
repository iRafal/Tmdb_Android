package com.tmdb.data.source.remote.implKtor.mapping

import com.tmdb.api.model.data.DataPage
import com.tmdb.api.model.movie.Movie
import com.tmdb.api.model.util.ApiResponse
import com.tmdb.api.model.util.NetworkErrorModel
import com.tmdb.data.api.config.url.image.contract.ImageUrlProvider
import com.tmdb.data.model.MovieDataModel
import com.tmdb.data.model.state.DataState
import javax.inject.Inject


interface MovieApiModelToDataStateModelMapper {
    fun map(input: ApiResponse<Movie, NetworkErrorModel>): DataState<MovieDataModel>
}

class MovieApiModelToDataStateModelMapperImpl @Inject constructor(
    private val movieApiModelToDataModelMapper: MovieApiModelToDataModelMapper
): MovieApiModelToDataStateModelMapper {
    override fun map(input: ApiResponse<Movie, NetworkErrorModel>): DataState<MovieDataModel> {
        return apiModelToDataStateMapperImpl(movieApiModelToDataModelMapper::map).invoke(input)
    }
}

interface MoviesListApiModelToDataStateModelMapper {
    fun map(input: ApiResponse<DataPage<Movie>, NetworkErrorModel>): DataState<List<MovieDataModel>>
}

class MoviesListApiModelToDataStateModelMapperImpl @Inject constructor(
    private val movieApiModelToDataModelMapper: MovieApiModelToDataModelMapper
): MoviesListApiModelToDataStateModelMapper {
    override fun map(input: ApiResponse<DataPage<Movie>, NetworkErrorModel>): DataState<List<MovieDataModel>> {
        return apiModelListToDataStateMapperImpl(movieApiModelToDataModelMapper::map).invoke(input)
    }
}

interface MovieApiModelToDataModelMapper {
    fun map(input: Movie): MovieDataModel
}

class MovieApiModelToDataModelMapperImpl @Inject constructor(
    private val imageUrlProvider: ImageUrlProvider
) : MovieApiModelToDataModelMapper {
    override fun map(input: Movie): MovieDataModel {
        return MovieDataModel(
            id = input.id,
            title = input.title,
            voteAverage = input.voteAverage,
            releaseDate = input.releaseDate,
            posterUrl = input.posterPath?.let { imageUrlProvider.posterUrl(it) }
        )
    }
}
