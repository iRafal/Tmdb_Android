package com.tmdb.data.local.impl.objectBox.movie

import com.tmdb.data.db.objectBox.movie.MovieEntity
import com.tmdb.data.db.objectBox.movie.dao.MovieDao
import com.tmdb.data.local.impl.objectBox.MovieLocalDataSourceImpl
import com.tmdb.data.local.impl.objectBox.di.UnitTestServiceLocator
import com.tmdb.data.local.impl.objectBox.mapping.MovieDataModelToEntityMapper
import com.tmdb.data.local.impl.objectBox.mapping.MovieEntityToDataModelMapper
import com.tmdb.data.local.impl.objectBox.util.ModelUtil
import com.tmdb.data.source.local.contract.MovieLocalDataSource
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.mockito.Mockito.times
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

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

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `observe all movies success`() = runTest {
        val allMoviesEntities = listOf(movieEntity)
        val allMovieDataModels = listOf(movieDataModel)
        whenever(movieEntityToDataModelMapper.map(movieEntity)).thenReturn(movieDataModel)
        val flow = flow {
            emit(allMoviesEntities)
        }
        whenever(movieDao.observeAll()).thenReturn(flow)
        movieLocalSource.observeAll().collect { result ->
            verify(movieDao, times(1)).observeAll()
            verify(movieEntityToDataModelMapper, times(1)).map(movieEntity)
            assertEquals(expected = allMovieDataModels, actual = result)
        }
    }

    @Test
    fun `observe all movies failure`() = runTest {
        val exception = IllegalStateException("Failed to observe get all movies")
        val flow = flow<List<MovieEntity>> {
            throw exception
        }
        whenever(movieDao.observeAll()).thenReturn(flow)
        movieLocalSource.observeAll().catch { e ->
            verify(movieDao, times(1)).observeAll()
            verify(movieEntityToDataModelMapper, times(0)).map(movieEntity)
            assertEquals(expected = e, actual = exception)
        }.collect()
    }

    @Test
    fun `get all movies success`() = runTest {
        val allMovieEntities = listOf(movieEntity)
        val allMovieDataModels = listOf(movieDataModel)
        whenever(movieDao.getAll()).thenReturn(allMovieEntities)
        whenever(movieEntityToDataModelMapper.map(movieEntity)).thenReturn(movieDataModel)
        movieLocalSource.getAll().also { result ->
            assertEquals(expected = allMovieDataModels, actual = result)
        }
        verify(movieDao, times(1)).getAll()
        verify(movieEntityToDataModelMapper, times(1)).map(movieEntity)
    }

    @Test
    fun `get all movies returns empty result`() = runTest {
        whenever(movieDao.getAll()).thenReturn(emptyList())
        movieLocalSource.getAll().also { result ->
            assertTrue(result.isEmpty())
        }
        verify(movieDao, times(1)).getAll()
        verify(movieEntityToDataModelMapper, times(0)).map(movieEntity)
    }

    @Test
    fun `get all movies failure`() = runTest {
        val exception = IllegalStateException("Failed to get all movies")
        whenever(movieDao.getAll()).thenThrow(exception)

        runCatching {
            movieLocalSource.getAll()
        }.onFailure { e ->
            verify(movieDao, times(1)).getAll()
            verify(movieEntityToDataModelMapper, times(0)).map(movieEntity)
            assertEquals(expected = e, actual = exception)
        }
    }

    @Test
    fun `get movie by id success`() = runTest {
        whenever(movieDao.getById(movieId)).thenReturn(movieEntity)
        whenever(movieEntityToDataModelMapper.map(movieEntity)).thenReturn(movieDataModel)
        movieLocalSource.getById(movieId).also { result ->
            assertEquals(expected = movieDataModel, actual = result)
        }
        verify(movieDao, times(1)).getById(movieId)
        verify(movieEntityToDataModelMapper, times(1)).map(movieEntity)
    }

    @Test
    fun `get movie by id returns null`() = runTest {
        whenever(movieDao.getById(movieId)).thenReturn(null)
        movieLocalSource.getById(movieId).also { result ->
            assertNull(result)
        }
        verify(movieDao, times(1)).getById(movieId)
        verify(movieEntityToDataModelMapper, times(0)).map(movieEntity)
    }

    @Test
    fun `get movie by id failure`() = runTest {
        val exception = IllegalStateException("Failed to get movies with id: $movieId")
        whenever(movieDao.getById(movieId)).thenThrow(exception)

        runCatching {
            movieLocalSource.getById(movieId)
        }.onFailure { e ->
            verify(movieDao, times(1)).getById(movieId)
            verify(movieEntityToDataModelMapper, times(0)).map(movieEntity)
            assertEquals(expected = e, actual = exception)
        }
    }

    @Test
    fun `insert movies success`() = runTest {
        whenever(movieDao.insert(movieEntity)).thenReturn(Unit)
        whenever(movieDataModelToEntityMapper.map(movieDataModel)).thenReturn(movieEntity)
        movieLocalSource.insert(movieDataModel)
        verify(movieDataModelToEntityMapper, times(1)).map(movieDataModel)
        verify(movieDao, times(1)).insert(movieEntity)
    }

    @Test
    fun `insert movies failure`() = runTest {
        val exception = IllegalStateException("Failed to insert movies with id: $movieId")
        whenever(movieDao.insert(movieEntity)).thenThrow(exception)

        runCatching {
            movieLocalSource.insert(movieDataModel)
        }.onFailure { e ->
            verify(movieDao, times(1)).insert(movieEntity)
            verify(movieDataModelToEntityMapper, times(0)).map(movieDataModel)
            assertEquals(expected = e, actual = exception)
        }
    }

    @Test
    fun `delete movies success`() = runTest {
        whenever(movieDataModelToEntityMapper.map(movieDataModel)).thenReturn(movieEntity)
        movieLocalSource.delete(movieDataModel)
        verify(movieDao, times(1)).delete(movieEntity)
        verify(movieDataModelToEntityMapper, times(1)).map(movieDataModel)
    }

    @Test
    fun `delete movies failure`() = runTest {
        val exception = IllegalStateException("Failed to delete movies with id: $movieId")
        whenever(movieDao.delete(movieEntity)).thenThrow(exception)

        runCatching {
            movieLocalSource.delete(movieDataModel)
        }.onFailure { e ->
            verify(movieDao, times(1)).delete(movieEntity)
            verify(movieDataModelToEntityMapper, times(0)).map(movieDataModel)
            assertEquals(expected = e, actual = exception)
        }
    }
}
