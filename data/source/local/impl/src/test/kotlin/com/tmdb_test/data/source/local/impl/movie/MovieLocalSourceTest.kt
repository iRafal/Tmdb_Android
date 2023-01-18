package com.tmdb_test.data.source.local.impl.movie

import com.tmdb_test.data.db.room.movie.MovieDao
import com.tmdb_test.data.db.room.movie.MovieEntity
import com.tmdb_test.data.source.local.impl.MovieLocalDataSourceImpl
import com.tmdb_test.data.source.local.impl.di.UnitTestServiceLocator
import com.tmdb_test.data.source.local.impl.movie.data.mapping.MovieDataModelToEntityMapper
import com.tmdb_test.data.source.local.impl.movie.data.mapping.MovieEntityToDataModelMapper
import com.tmdb_test.data.source.local.impl.util.ModelUtil
import com.tmdb_test.data.source.remote.contract.MovieLocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.times
import org.mockito.Mockito.`when`
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@OptIn(ExperimentalCoroutinesApi::class)
class MovieLocalSourceTest {
    private val movieDao = mock<MovieDao>()
    private val movieEntityToDataModelMapper = mock<MovieEntityToDataModelMapper>()
    private val movieDataModelToEntityMapper = mock<MovieDataModelToEntityMapper>()
    private val movieLocalSource: MovieLocalDataSource = MovieLocalDataSourceImpl(
        movieDao,
        movieEntityToDataModelMapper,
        movieDataModelToEntityMapper
    )
    private val dispatcher = UnitTestServiceLocator.textDispatcher

    private val movieId = ModelUtil.movieId
    private val movieEntity = ModelUtil.movieEntity
    private val movieDataModel = ModelUtil.movieDataModel

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `observe all movies success`() = runTest {
        val allMoviesEntities = listOf(movieEntity)
        val allMovieDataModels = listOf(movieDataModel)
        `when`(movieEntityToDataModelMapper.invoke(movieEntity)).thenReturn(movieDataModel)
        val flow = flow {
            emit(allMoviesEntities)
        }
        `when`(movieDao.observeAll()).thenReturn(flow)
        movieLocalSource.observeAll().collect { result ->
            verify(movieDao, times(1)).observeAll()
            verify(movieEntityToDataModelMapper, times(1)).invoke(movieEntity)
            assertEquals(allMovieDataModels, result)
        }
    }

    @Test
    fun `observe all movies failure`() = runTest {
        val exception = IllegalStateException("Failed to observe get all movies")
        val flow = flow<List<MovieEntity>> {
            throw exception
        }
        `when`(movieDao.observeAll()).thenReturn(flow)
        movieLocalSource.observeAll().catch { e ->
            verify(movieDao, times(1)).observeAll()
            verify(movieEntityToDataModelMapper, times(0)).invoke(movieEntity)
            assertEquals(e, exception)
        }.collect()
    }

    @Test
    fun `get all movies success`() = runTest {
        val allMovieEntities = listOf(movieEntity)
        val allMovieDataModels = listOf(movieDataModel)
        `when`(movieDao.getAll()).thenReturn(allMovieEntities)
        `when`(movieEntityToDataModelMapper.invoke(movieEntity)).thenReturn(movieDataModel)
        movieLocalSource.getAll().also { result ->
            assertEquals(allMovieDataModels, result)
        }
        verify(movieDao, times(1)).getAll()
        verify(movieEntityToDataModelMapper, times(1)).invoke(movieEntity)
    }

    @Test
    fun `get all movies returns empty result`() = runTest {
        `when`(movieDao.getAll()).thenReturn(emptyList())
        movieLocalSource.getAll().also { result ->
            assertTrue(result.isEmpty())
        }
        verify(movieDao, times(1)).getAll()
        verify(movieEntityToDataModelMapper, times(0)).invoke(movieEntity)
    }

    @Test
    fun `get all movies failure`() = runTest {
        val exception = IllegalStateException("Failed to get all movies")
        `when`(movieDao.getAll()).thenThrow(exception)

        runCatching {
            movieLocalSource.getAll()
        }.onFailure { e ->
            verify(movieDao, times(1)).getAll()
            verify(movieEntityToDataModelMapper, times(0)).invoke(movieEntity)
            assertEquals(e, exception)
        }
    }

    @Test
    fun `get movie by id success`() = runTest {
        `when`(movieDao.getById(movieId)).thenReturn(movieEntity)
        `when`(movieEntityToDataModelMapper.invoke(movieEntity)).thenReturn(movieDataModel)
        movieLocalSource.getById(movieId).also { result ->
            assertEquals(movieDataModel, result)
        }
        verify(movieDao, times(1)).getById(movieId)
        verify(movieEntityToDataModelMapper, times(1)).invoke(movieEntity)
    }

    @Test
    fun `get movie by id returns null`() = runTest {
        `when`(movieDao.getById(movieId)).thenReturn(null)
        movieLocalSource.getById(movieId).also { result ->
            assertNull(result)
        }
        verify(movieDao, times(1)).getById(movieId)
        verify(movieEntityToDataModelMapper, times(0)).invoke(movieEntity)
    }

    @Test
    fun `get movie by id failure`() = runTest {
        val exception = IllegalStateException("Failed to get movies with id: $movieId")
        `when`(movieDao.getById(movieId)).thenThrow(exception)

        runCatching {
            movieLocalSource.getById(movieId)
        }.onFailure { e ->
            verify(movieDao, times(1)).getById(movieId)
            verify(movieEntityToDataModelMapper, times(0)).invoke(movieEntity)
            assertEquals(e, exception)
        }
    }

    @Test
    fun `insert movies success`() = runTest {
        `when`(movieDao.insert(movieEntity)).thenReturn(Unit)
        `when`(movieDataModelToEntityMapper.invoke(movieDataModel)).thenReturn(movieEntity)
        movieLocalSource.insert(movieDataModel)
        verify(movieDataModelToEntityMapper, times(1)).invoke(movieDataModel)
        verify(movieDao, times(1)).insert(movieEntity)
    }

    @Test
    fun `insert movies failure`() = runTest {
        val exception = IllegalStateException("Failed to insert movies with id: $movieId")
        `when`(movieDao.insert(movieEntity)).thenThrow(exception)

        runCatching {
            movieLocalSource.insert(movieDataModel)
        }.onFailure { e ->
            verify(movieDao, times(1)).insert(movieEntity)
            verify(movieDataModelToEntityMapper, times(0)).invoke(movieDataModel)
            assertEquals(e, exception)
        }
    }

    @Test
    fun `delete movies success`() = runTest {
        `when`(movieDataModelToEntityMapper.invoke(movieDataModel)).thenReturn(movieEntity)
        movieLocalSource.delete(movieDataModel)
        verify(movieDao, times(1)).delete(movieEntity)
        verify(movieDataModelToEntityMapper, times(1)).invoke(movieDataModel)
    }

    @Test
    fun `delete movies failure`() = runTest {
        val exception = IllegalStateException("Failed to delete movies with id: $movieId")
        `when`(movieDao.delete(movieEntity)).thenThrow(exception)

        runCatching {
            movieLocalSource.delete(movieDataModel)
        }.onFailure { e ->
            verify(movieDao, times(1)).delete(movieEntity)
            verify(movieDataModelToEntityMapper, times(0)).invoke(movieDataModel)
            assertEquals(e, exception)
        }
    }
}