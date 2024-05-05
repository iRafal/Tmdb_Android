package com.tmdb.data.local.impl.objectBox

import com.tmdb.data.db.objectBox.movie.MovieEntity
import com.tmdb.data.db.objectBox.movie.dao.MovieDao
import com.tmdb.data.local.impl.objectBox.mapping.MovieDataModelToEntityMapper
import com.tmdb.data.local.impl.objectBox.mapping.MovieEntityToDataModelMapper
import com.tmdb.data.model.MovieDataModel
import com.tmdb.data.source.local.contract.MovieLocalDataSource
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Suppress("TooManyFunctions")
class MovieLocalDataSourceImpl @Inject constructor(
    private val movieDao: MovieDao,
    private val movieEntityToDataModelMapper: @JvmSuppressWildcards MovieEntityToDataModelMapper,
    private val movieDataModelToEntityMapper: @JvmSuppressWildcards MovieDataModelToEntityMapper
) : MovieLocalDataSource {

    override suspend fun movie(movieId: Int): MovieDataModel? {
        return movieDao.getById(movieId)?.let { movieEntityToDataModelMapper.map(it) }
    }

    override suspend fun nowPlayingMovies(
        page: Int?,
        pageSize: Int?
    ): List<MovieDataModel> {
        val entities = movieDao.nowPlayingMovies(page = page, pageSize = pageSize)
        return entities.map { movieEntityToDataModelMapper.map(it) }
    }

    override suspend fun nowPopularMovies(
        page: Int?,
        pageSize: Int?
    ): List<MovieDataModel> {
        val entities = movieDao.nowPopularMovies(page = page, pageSize = pageSize)
        return entities.map { movieEntityToDataModelMapper.map(it) }
    }

    override suspend fun topRatedMovies(
        page: Int?,
        pageSize: Int?
    ): List<MovieDataModel> {
        val entities = movieDao.topRatedMovies(page = page, pageSize = pageSize)
        return entities.map { movieEntityToDataModelMapper.map(it) }
    }

    override suspend fun upcomingMovies(
        page: Int?,
        pageSize: Int?
    ): List<MovieDataModel> {
        val entities = movieDao.upcomingMovies(page = page, pageSize = pageSize)
        return entities.map { movieEntityToDataModelMapper.map(it) }
    }

    override suspend fun insert(movie: MovieDataModel) {
        movieDao.insert(movieDataModelToEntityMapper.map(movie))
    }

    override suspend fun insertAll(movies: List<MovieDataModel>) {
        val mapped = movies.map { movieDataModelToEntityMapper.map(it) }
        movieDao.insert(mapped)
    }

    override suspend fun insertByCategories(
        nowPlaying: List<MovieDataModel>,
        nowPopular: List<MovieDataModel>,
        topRatedMovies: List<MovieDataModel>,
        upcomingMovies: List<MovieDataModel>
    ) {
        val mappedNowPlaying =
            nowPlaying.map { movieDataModelToEntityMapper.map(it).copy(isNowPlaying = true) }
        val mappedNowPopular =
            nowPlaying.map { movieDataModelToEntityMapper.map(it).copy(isNowPopular = true) }
        val mappedTopRatedMovies =
            nowPlaying.map { movieDataModelToEntityMapper.map(it).copy(isTopRated = true) }
        val mappedUpcomingMovies =
            nowPlaying.map { movieDataModelToEntityMapper.map(it).copy(isUpcoming = true) }

        val allItems =
            mappedNowPlaying + mappedNowPopular + mappedTopRatedMovies + mappedUpcomingMovies
        if (allItems.isEmpty()) return

        val mergedItemsList = mutableListOf<MovieEntity>()
        val groupedByIdItems = allItems.groupBy { it.movieId }
        groupedByIdItems.forEach { (_, list) ->
            if (list.isNotEmpty()) {
                val mergedItem = list.first().copy(
                    isNowPlaying = list.any { it.isNowPlaying },
                    isNowPopular = list.any { it.isNowPopular },
                    isTopRated = list.any { it.isTopRated },
                    isUpcoming = list.any { it.isUpcoming }
                )
                mergedItemsList += mergedItem
            }
        }
        movieDao.insert(mergedItemsList)
    }

    override suspend fun delete(movie: MovieDataModel) {
        movieDao.delete(movieDataModelToEntityMapper.map(movie))
    }

    override fun observeAll(): Flow<List<MovieDataModel>> {
        return movieDao.observeAll().map { it.map(movieEntityToDataModelMapper::map) }
    }

    override suspend fun getAll(): List<MovieDataModel> {
        return movieDao.getAll().map(movieEntityToDataModelMapper::map)
    }

    override suspend fun getById(id: Int): MovieDataModel? {
        return movieDao.getById(id)?.let { movieEntityToDataModelMapper.map(it) }
    }
}
