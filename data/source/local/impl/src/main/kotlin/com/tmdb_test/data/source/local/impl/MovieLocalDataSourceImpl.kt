package com.tmdb_test.data.source.local.impl

import com.tmdb_test.data.db.room.movie.MovieDao
import com.tmdb_test.data.db.room.movie.MovieEntity
import com.tmdb_test.data.model.movie.MovieDataModel
import com.tmdb_test.data.source.local.impl.movie.data.mapping.MovieDataModelToEntityMapper
import com.tmdb_test.data.source.local.impl.movie.data.mapping.MovieEntityToDataModelMapper
import com.tmdb_test.data.source.remote.contract.MovieLocalDataSource
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieLocalDataSourceImpl @Inject constructor(
    private val movieDao: MovieDao,
    private val movieEntityToDataModelMapper: @JvmSuppressWildcards MovieEntityToDataModelMapper,
    private val movieDataModelToEntityMapper: @JvmSuppressWildcards MovieDataModelToEntityMapper
) : MovieLocalDataSource {

    override suspend fun movie(movieId: Int): MovieDataModel? {
        return movieDao.getById(movieId)?.let { movieEntityToDataModelMapper(it) }
    }

    override suspend fun nowPlayingMovies(
        page: Int?,
        pageSize: Int?,
    ): List<MovieDataModel> {
        val limitAndOffset = processPageData(page, pageSize)
        val entities = if (limitAndOffset == null) {
            movieDao.nowPlayingMovies()
        } else {
            movieDao.nowPlayingMovies(limit = limitAndOffset.first, offset = limitAndOffset.second)
        }
        return entities.map { movieEntityToDataModelMapper(it) }
    }

    override suspend fun nowPopularMovies(
        page: Int?,
        pageSize: Int?,
    ): List<MovieDataModel> {
        val limitAndOffset = processPageData(page, pageSize)
        val entities = if (limitAndOffset == null) {
            movieDao.nowPopularMovies()
        } else {
            movieDao.nowPopularMovies(limit = limitAndOffset.first, offset = limitAndOffset.second)
        }
        return entities.map { movieEntityToDataModelMapper(it) }
    }

    override suspend fun topRatedMovies(
        page: Int?,
        pageSize: Int?,
    ): List<MovieDataModel> {
        val limitAndOffset = processPageData(page, pageSize)
        val entities = if (limitAndOffset == null) {
            movieDao.topRatedMovies()
        } else {
            movieDao.topRatedMovies(limit = limitAndOffset.first, offset = limitAndOffset.second)
        }
        return entities.map { movieEntityToDataModelMapper(it) }
    }

    override suspend fun upcomingMovies(
        page: Int?,
        pageSize: Int?,
    ): List<MovieDataModel> {
        val limitAndOffset = processPageData(page, pageSize)
        val entities = if (limitAndOffset == null) {
            movieDao.upcomingMovies()
        } else {
            movieDao.upcomingMovies(limit = limitAndOffset.first, offset = limitAndOffset.second)
        }
        return entities.map { movieEntityToDataModelMapper(it) }
    }

    private fun processPageData(
        page: Int?,
        pageSize: Int?,
    ): Pair<Int, Int>? {
        val _page = page ?: 0
        val _pageSize = pageSize ?: 0
        if (_page == 0 && _pageSize == 0) return null

        return if (page == 0) {
            _pageSize to 0
        } else {
            val currentPage = _page - 1
            val limit = currentPage * _pageSize
            val offset = _page * _pageSize
            limit to offset
        }
    }

    override suspend fun insert(movie: MovieDataModel) {
        movieDao.insert(movieDataModelToEntityMapper(movie))
    }

    override suspend fun insertAll(movies: List<MovieDataModel>) {
        val mapped = movies.map { movieDataModelToEntityMapper(it) }
        movieDao.insert(mapped)
    }

    override suspend fun insertByCategories(
        nowPlaying: List<MovieDataModel>,
        nowPopular: List<MovieDataModel>,
        topRatedMovies: List<MovieDataModel>,
        upcomingMovies: List<MovieDataModel>
    ) {
        val mappedNowPlaying =
            nowPlaying.map { movieDataModelToEntityMapper(it).copy(isNowPlaying = true) }
        val mappedNowPopular =
            nowPlaying.map { movieDataModelToEntityMapper(it).copy(isNowPopular = true) }
        val mappedTopRatedMovies =
            nowPlaying.map { movieDataModelToEntityMapper(it).copy(isTopRated = true) }
        val mappedUpcomingMovies =
            nowPlaying.map { movieDataModelToEntityMapper(it).copy(isUpcoming = true) }

        val allItems =
            mappedNowPlaying + mappedNowPopular + mappedTopRatedMovies + mappedUpcomingMovies
        if (allItems.isEmpty()) return

        val mergedItemsList = mutableListOf<MovieEntity>()
        val groupedByIdItems = allItems.groupBy { it.id }
        groupedByIdItems.forEach { (_, list) ->
            if (list.isNotEmpty()) {
                var mergedItem = list.first()
                list.forEach {
                    if (it != mergedItem) {
                        mergedItem = mergedItem.copy(
                            isNowPlaying = if (it.isNowPlaying) true else mergedItem.isNowPlaying,
                            isNowPopular = if (it.isNowPopular) true else mergedItem.isNowPopular,
                            isTopRated = if (it.isTopRated) true else mergedItem.isTopRated,
                            isUpcoming = if (it.isUpcoming) true else mergedItem.isUpcoming,
                        )
                    }
                }
                mergedItemsList += mergedItem
            }
        }
        movieDao.insert(mergedItemsList)
    }

    override suspend fun delete(movie: MovieDataModel) {
        movieDao.delete(movieDataModelToEntityMapper(movie))
    }

    override fun observeAll(): Flow<List<MovieDataModel>> {
        return movieDao.observeAll().map { it.map(movieEntityToDataModelMapper) }
    }

    override suspend fun getAll(): List<MovieDataModel> {
        return movieDao.getAll().map(movieEntityToDataModelMapper)
    }

    override suspend fun getById(id: Int): MovieDataModel? {
        return movieDao.getById(id)?.let { movieEntityToDataModelMapper(it) }
    }
}