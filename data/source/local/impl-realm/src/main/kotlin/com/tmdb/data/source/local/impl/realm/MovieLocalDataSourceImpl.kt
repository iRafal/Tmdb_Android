package com.tmdb.data.source.local.impl.realm

import com.tmdb.data.db.realm.movie.MovieEntity
import com.tmdb.data.db.realm.movie.dao.MovieDao
import com.tmdb.data.model.MovieDataModel
import com.tmdb.data.source.local.contract.MovieLocalDataSource
import com.tmdb.data.source.local.impl.realm.mapping.MovieDataModelToEntityMapper
import com.tmdb.data.source.local.impl.realm.mapping.MovieEntityToDataModelMapper
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Suppress("TooManyFunctions")
class MovieLocalDataSourceImpl @Inject constructor(
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
            nowPlaying.map { movieDataModelToEntityMapper.map(it).apply { isNowPlaying = true } }
        val mappedNowPopular =
            nowPlaying.map { movieDataModelToEntityMapper.map(it).apply { isNowPopular = true } }
        val mappedTopRatedMovies =
            nowPlaying.map { movieDataModelToEntityMapper.map(it).apply { isTopRated = true } }
        val mappedUpcomingMovies =
            nowPlaying.map { movieDataModelToEntityMapper.map(it).apply { isUpcoming = true } }

        val allItems =
            mappedNowPlaying + mappedNowPopular + mappedTopRatedMovies + mappedUpcomingMovies
        if (allItems.isEmpty()) return

        val mergedItemsList = mutableListOf<MovieEntity>()
        val groupedByIdItems = allItems.groupBy { it.id }
        groupedByIdItems.forEach { (_, list) ->
            if (list.isNotEmpty()) {
                val mergedItem = list.first().apply {
                    isNowPlaying = list.any { it.isNowPlaying }
                    isNowPopular = list.any { it.isNowPopular }
                    isTopRated = list.any { it.isTopRated }
                    isUpcoming = list.any { it.isUpcoming }
                }
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
