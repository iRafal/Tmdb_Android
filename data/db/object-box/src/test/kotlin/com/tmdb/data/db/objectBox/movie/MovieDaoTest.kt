package com.tmdb.data.db.objectBox.movie

import com.tmdb.data.db.objectBox.movie.dao.MovieDao
import com.tmdb.data.db.objectBox.movie.dao.MovieDaoImpl
import com.tmdb.data.db.objectBox.util.BaseObjectBoxTest
import com.tmdb.data.db.objectBox.util.ModelUtil
import io.objectbox.kotlin.boxFor
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runTest

class MovieDaoTest : BaseObjectBoxTest() {
    private lateinit var movieDao: MovieDao

    override fun setUp() {
        super.setUp()
        movieDao = MovieDaoImpl(store.boxFor())
    }

    @Test
    fun `insert duplicate`() = runTest {
        val input = ModelUtil.movieEntity
        movieDao.insert(input)

        movieDao.getAll().run {
            assertTrue(this.size == 1)
            assertEquals(expected = input.movieId, actual = this.first().movieId)
        }

        movieDao.insert(input)
        movieDao.getAll().run {
            assertTrue(this.size == 1)
            assertEquals(expected = input.movieId, actual = this.first().movieId)
        }
    }

    @Test
    fun `observe all movies success`() = runTest {
        val expected = listOf(ModelUtil.movieEntity)
        movieDao.insert(expected)
        val actual = movieDao.observeAll().take(1).firstOrNull()

        assertEquals(expected = expected, actual = actual)
    }

    @Test
    fun `observe all movies failure`() = runTest {
        val actual = movieDao.observeAll().take(1).firstOrNull()

        assertEquals(expected = emptyList(), actual = actual)
    }

    @Test
    fun `get all movies success`() = runTest {
        val expected = listOf(ModelUtil.movieEntity)
        movieDao.insert(expected)
        val actual = movieDao.getAll()

        assertEquals(expected = expected, actual = actual)
    }

    @Test
    fun `get all movies returns empty result`() = runTest {
        val actual = movieDao.getAll()

        assertEquals(expected = emptyList(), actual = actual)
    }

    @Test
    fun `get movie by id success`() = runTest {
        val expected = ModelUtil.movieEntity
        movieDao.insert(expected)
        val actual = movieDao.getById(ModelUtil.movieId)

        assertEquals(expected = expected, actual = actual)
    }

    @Test
    fun `get movie by id returns null`() = runTest {
        val actual = movieDao.getById(ModelUtil.movieId)

        assertNull(actual)
    }

    @Test
    fun `delete movies success`() = runTest {
        val input = ModelUtil.movieEntity
        movieDao.insert(input)
        assertEquals(expected = input, actual = movieDao.getById(ModelUtil.movieId))
        movieDao.delete(input)
        assertTrue(movieDao.getAll().isEmpty())
    }
}
