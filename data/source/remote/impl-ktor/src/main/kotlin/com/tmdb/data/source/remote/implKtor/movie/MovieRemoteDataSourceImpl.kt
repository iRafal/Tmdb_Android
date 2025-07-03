package com.tmdb.data.source.remote.implKtor.movie

import com.tmdb.data.api.implKtor.movie.MovieApi
import com.tmdb.data.model.MovieDataModel
import com.tmdb.data.model.state.DataState
import com.tmdb.data.source.remote.contract.movie.MovieRemoteDataSource
import com.tmdb.data.source.remote.implKtor.mapping.MovieApiModelToDataStateModelMapper
import com.tmdb.data.source.remote.implKtor.mapping.MoviesListApiModelToDataStateModelMapper

class MovieRemoteDataSourceImpl(
    private val api: MovieApi,
    private val movieApiModelToDataStateModelMapper: MovieApiModelToDataStateModelMapper,
    private val moviesListApiModelToDataStateModelMapper: MoviesListApiModelToDataStateModelMapper,
) : MovieRemoteDataSource {
    override suspend fun movie(
        movieId: Int,
        language: String?,
        appendToResponse: String?
    ): DataState<MovieDataModel> {
        return movieApiModelToDataStateModelMapper.map(api.movie(movieId, language, appendToResponse))
    }

    override suspend fun latestMovie(
        language: String?
    ): DataState<MovieDataModel> = movieApiModelToDataStateModelMapper.map(api.latestMovie(language))

    override suspend fun nowPlayingMovies(
        language: String?,
        page: Int?,
        region: String?
    ): DataState<List<MovieDataModel>> {
        return moviesListApiModelToDataStateModelMapper.map(api.nowPlayingMovies(language, page, region))
    }

    override suspend fun nowPopularMovies(
        language: String?,
        page: Int?,
        region: String?
    ): DataState<List<MovieDataModel>> =
        moviesListApiModelToDataStateModelMapper.map(api.nowPopularMovies(language, page, region))

    override suspend fun topRatedMovies(
        language: String?,
        page: Int?,
        region: String?
    ): DataState<List<MovieDataModel>> {
        return moviesListApiModelToDataStateModelMapper.map(api.topRatedMovies(language, page, region))
    }

    override suspend fun upcomingMovies(
        language: String?,
        page: Int?,
        region: String?
    ): DataState<List<MovieDataModel>> {
        return moviesListApiModelToDataStateModelMapper.map(api.upcomingMovies(language, page, region))
    }
}
