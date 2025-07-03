package com.tmdb.data.source.local.implRoom

import com.tmdb.data.db.room.movie.MovieDao
import com.tmdb.data.db.room.movie.MovieEntity
import com.tmdb.data.model.MovieDataModel
import com.tmdb.data.source.local.contract.MovieLocalDataSource
import com.tmdb.data.source.local.implRoom.mapping.MovieDataModelToEntityMapper
import com.tmdb.data.source.local.implRoom.mapping.MovieEntityToDataModelMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Suppress("TooManyFunctions")
class MovieLocalDataSourceImpl(
    private val movieDao: MovieDao,
    private val movieEntityToDataModelMapper: MovieEntityToDataModelMapper,
    private val movieDataModelToEntityMapper: MovieDataModelToEntityMapper
) : MovieLocalDataSource {

    override suspend fun movie(movieId: Int): MovieDataModel? {
        return movieDao.getById(movieId)?.let { movieEntityToDataModelMapper.map(it) }
    }

    override suspend fun nowPlayingMovies(
        page: Int?,
        pageSize: Int?
    ): List<MovieDataModel> {
        val limitAndOffset = processPageData(page, pageSize)
        val entities = if (limitAndOffset == null) {
            movieDao.nowPlayingMovies()
        } else {
            movieDao.nowPlayingMovies(limit = limitAndOffset.first, offset = limitAndOffset.second)
        }
        return entities.map { movieEntityToDataModelMapper.map(it) }
    }

    override suspend fun nowPopularMovies(
        page: Int?,
        pageSize: Int?
    ): List<MovieDataModel> {
        val limitAndOffset = processPageData(page, pageSize)
        val entities = if (limitAndOffset == null) {
            movieDao.nowPopularMovies()
        } else {
            movieDao.nowPopularMovies(limit = limitAndOffset.first, offset = limitAndOffset.second)
        }
        return entities.map { movieEntityToDataModelMapper.map(it) }
    }

    override suspend fun topRatedMovies(
        page: Int?,
        pageSize: Int?
    ): List<MovieDataModel> {
        val limitAndOffset = processPageData(page, pageSize)
        val entities = if (limitAndOffset == null) {
            movieDao.topRatedMovies()
        } else {
            movieDao.topRatedMovies(limit = limitAndOffset.first, offset = limitAndOffset.second)
        }
        return entities.map { movieEntityToDataModelMapper.map(it) }
    }

    override suspend fun upcomingMovies(
        page: Int?,
        pageSize: Int?
    ): List<MovieDataModel> {
        val limitAndOffset = processPageData(page, pageSize)
        val entities = if (limitAndOffset == null) {
            movieDao.upcomingMovies()
        } else {
            movieDao.upcomingMovies(limit = limitAndOffset.first, offset = limitAndOffset.second)
        }
        return entities.map { movieEntityToDataModelMapper.map(it) }
    }

    private fun processPageData(
        page: Int?,
        pageSize: Int?
    ): Pair<Int, Int>? {
        val pageOrDefault = page ?: 0
        val pageSizeOrDefault = pageSize ?: 0
        if (pageOrDefault == 0 && pageSizeOrDefault == 0) return null

        return if (page == 0) {
            pageSizeOrDefault to 0
        } else {
            val currentPage = pageOrDefault - 1
            val limit = currentPage * pageSizeOrDefault
            val offset = pageOrDefault * pageSizeOrDefault
            limit to offset
        }
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
        val groupedByIdItems = allItems.groupBy { it.id }
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
