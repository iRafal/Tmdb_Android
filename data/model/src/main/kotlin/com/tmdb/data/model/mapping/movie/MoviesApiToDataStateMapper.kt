package com.tmdb.data.model.mapping.movie

import com.tmdb.api.model.data.DataPage
import com.tmdb.api.model.movie.Movie
import com.tmdb.api.model.util.ApiResponse
import com.tmdb.api.model.util.NetworkErrorModel
import com.tmdb.data.model.mapping.mapApiToDataState
import com.tmdb.data.model.movie.MovieDataModel
import com.tmdb.data.model.state.DataState

typealias MoviesApiToDataStateMapper = (
    input: ApiResponse<DataPage<Movie>, NetworkErrorModel>
) -> DataState<List<MovieDataModel>>

fun moviesApiToDataStateMapperImpl(
    movieApiToDataModelMapper: @JvmSuppressWildcards MovieApiToDataModelMapper
): MoviesApiToDataStateMapper {
    return { input: ApiResponse<DataPage<Movie>, NetworkErrorModel> ->
        val dataMapper: (DataPage<Movie>) -> List<MovieDataModel> = { dataPage ->
            dataPage.results.map { movieApiToDataModelMapper(it) }
        }
        input.mapApiToDataState(dataMapper)
    }
}
