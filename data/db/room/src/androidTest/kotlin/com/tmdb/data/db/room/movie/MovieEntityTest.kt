package com.tmdb.data.db.room.movie

import com.tmdb.data.db.room.KoinAndroidTestRule
import com.tmdb.data.db.room.MovieDb
import com.tmdb.data.db.room.di.module.DISPATCHER_TEST_STANDARD
import com.tmdb.data.db.room.di.module.DISPATCHER_TEST_UNCONFINED
import com.tmdb.data.db.room.util.ModelUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.koin.core.qualifier.named
import org.koin.test.KoinTest
import org.koin.test.inject
import java.io.IOException
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class MovieEntityTest: KoinTest {
    @get:Rule
    val rule = KoinAndroidTestRule()

    private val dispatcher: TestDispatcher by inject(named(DISPATCHER_TEST_STANDARD))
    private val movieDao: MovieDao by inject()

    private val db: MovieDb by inject()

    private val movieEntity = ModelUtil.movieEntity
    private val movieId = ModelUtil.movieId

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @AfterTest
    @Throws(IOException::class)
    fun tearDown() = runTest {
        movieDao.delete()
        db.close()
        Dispatchers.resetMain()
    }

    @Test
    @Throws(IOException::class)
    fun write_GetMovieById() = runTest {
        movieDao.insert(movieEntity)
        movieDao.getById(movieId).run { assertEquals(expected = movieEntity, actual = this) }
    }

    @Test
    @Throws(IOException::class)
    fun addNothing_GetMovieById() = runTest {
        movieDao.getById(movieId).run { assertNull(this) }
    }

    @Test
    @Throws(IOException::class)
    fun write_GetAllMovieEntities() = runTest {
        movieDao.insert(movieEntity)
        movieDao.getAll().run { assertTrue(this.contains(movieEntity)) }
    }

    @Test
    @Throws(IOException::class)
    fun addNothing_GetAllMovies() = runTest {
        movieDao.getAll().run { assertTrue(this.isEmpty()) }
    }

    @Test
    @Throws(IOException::class)
    fun write_ObserveAllMovies() = runTest {
        movieDao.insert(movieEntity)
        val allMovies = movieDao.observeAll().take(1).firstOrNull()
        assertTrue(allMovies?.contains(movieEntity) == true)
    }

    @Test
    @Throws(IOException::class)
    fun addNothing_ObserveAllMovies() = runTest {
        val allMovies = movieDao.observeAll().take(1).firstOrNull()
        advanceUntilIdle()
        assertEquals(expected = allMovies, actual = emptyList())
    }

    @Test
    @Throws(IOException::class)
    fun write_DeleteMovieById() = runTest {
        movieDao.insert(movieEntity)
        movieDao.getById(movieId).run { assertEquals(expected = movieEntity, actual = this) }
        movieDao.delete(movieEntity)
        movieDao.getById(movieId).run { assertNull(this) }
    }

    @Test
    @Throws(IOException::class)
    fun addNothing_DeleteMovieById() = runTest {
        movieDao.getById(movieId).run { assertNull(this) }
        movieDao.delete(movieEntity).also { removedRows -> assertEquals(removedRows, 0) }
    }

    @Test
    @Throws(IOException::class)
    fun write_DeleteAllMovies() = runTest {
        movieDao.insert(movieEntity)
        movieDao.getById(movieId).run { assertEquals(expected = movieEntity, actual = this) }
        movieDao.delete()
        movieDao.getById(movieId).also { deletedMovie -> assertNull(deletedMovie) }
    }

    @Test
    @Throws(IOException::class)
    fun addNothing_DeleteAllMovies() = runTest {
        movieDao.getById(movieId).run { assertNull(this) }
        movieDao.delete().also { removedRows -> assertEquals(expected = removedRows, actual = 0) }
    }
}
