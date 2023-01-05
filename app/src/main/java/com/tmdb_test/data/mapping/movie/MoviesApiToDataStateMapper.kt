package com.tmdb_test.data.mapping.movie

import com.tmdb_test.api.impl_retrofit.util.ApiResponse
import com.tmdb_test.api.impl_retrofit.util.NetworkErrorModel
import com.tmdb_test.api.model.data.DataPage
import com.tmdb_test.api.model.movie.Movie
import com.tmdb_test.data.mapping.mapApiToDataState
import com.tmdb_test.data.model.DataState
import com.tmdb_test.data.model.MovieDataModel

typealias MoviesApiToDataStateMapper = (
    input: ApiResponse<DataPage<Movie>, NetworkErrorModel>
) -> DataState<List<MovieDataModel>>

fun moviesApiToDataStateMapperImpl(
    movieApiToDataModelMapper: @JvmSuppressWildcards MovieApiToDataModelMapper,
): MoviesApiToDataStateMapper {
    return { input: ApiResponse<DataPage<Movie>, NetworkErrorModel> ->
        val dataMapper: (DataPage<Movie>) -> List<MovieDataModel> = { dataPage ->
            dataPage.results.map { movieApiToDataModelMapper(it) }
        }
        input.mapApiToDataState(dataMapper)
    }
}