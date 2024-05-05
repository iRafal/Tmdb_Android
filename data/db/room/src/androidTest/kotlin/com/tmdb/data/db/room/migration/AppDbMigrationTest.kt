package com.tmdb.data.db.room.migration

import android.content.Context
import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.espresso.internal.inject.InstrumentationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.tmdb.data.db.room.MovieDb
import com.tmdb.data.db.room.MovieDbMigrations
import com.tmdb.data.db.room.di.component.app.TestAppComponentStore
import com.tmdb.data.db.room.di.component.db.TestDbComponent
import com.tmdb.data.db.room.di.module.DispatchersTestModule
import com.tmdb.data.db.room.movie.MovieEntity
import com.tmdb.data.db.room.util.ModelUtil
import java.io.IOException
import javax.inject.Inject
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class AppDbMigrationTest {

    @Inject
    @DispatchersTestModule.DispatcherTestUnconfined
    lateinit var dispatcher: TestDispatcher

    @Inject
    @InstrumentationContext
    lateinit var context: Context

    @get:Rule
    val helper: MigrationTestHelper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        MovieDb::class.java.canonicalName,
        FrameworkSQLiteOpenHelperFactory()
    )

   private lateinit var testDbComponent: TestDbComponent

    @BeforeTest
    fun setup() {
        testDbComponent = TestAppComponentStore.component.testDbComponentBuilder.build()
        testDbComponent.inject(this)
        Dispatchers.setMain(dispatcher)
    }

    @AfterTest
    @Throws(IOException::class)
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    @Throws(IOException::class)
    fun migrate1To2() = runTest {
        val versionFrom = 1
        val versionTo = 2

        val movieId = ModelUtil.movieId
        val title = ModelUtil.title
        val voteAverage = ModelUtil.voteAverage
        val releaseDate = ModelUtil.releaseDate
        val posterUrl = ModelUtil.posterUrl
        val isNowPlaying = ModelUtil.isNowPlaying
        val isNowPopular = ModelUtil.isNowPopular
        val isTopRated = ModelUtil.isTopRated
        val isUpcoming = ModelUtil.isUpcoming

        val tableName = MovieEntity.MOVIE_TABLE_NAME
        val columId = MovieEntity.MOVIE_TABLE_COLUMN_ID
        val columnTitle = MovieEntity.MOVIE_TABLE_COLUMN_TITLE
        val columnVoteAverage = MovieEntity.MOVIE_TABLE_COLUMN_VOTE_AVERAGE
        val columnReleaseDate = MovieEntity.MOVIE_TABLE_COLUMN_RELEASE_DATE
        val columnPosterUrl = MovieEntity.MOVIE_TABLE_COLUMN_POSTER_URL

        helper.createDatabase(TEST_DB, versionFrom).apply {
            //INFO: db has schema version 1. insert some data using SQL queries.
            //INFO: You cannot use DAO classes because they expect the latest schema.
            execSQL(
                "INSERT INTO $tableName " +
                        "($columId, $columnTitle, $columnVoteAverage, $columnReleaseDate, $columnPosterUrl) " +
                        "VALUES ('$movieId', '$title', '$voteAverage', '$releaseDate', '$posterUrl')"
            )

            //INFO: Prepare for the next version.
            close()
        }

        val db = helper.runMigrationsAndValidate(
            TEST_DB,
            versionTo,
            true,
            MovieDbMigrations.MIGRATION_1_2
        )

        //INFO: MigrationTestHelper automatically verifies the schema changes,
        //INFO: but you need to validate that the data was migrated properly.
        val migratedDb = getMigratedRoomDatabase()
        val actualMovie = migratedDb.movieDao().getAll().firstOrNull()
        checkNotNull(actualMovie)

        assertEquals(expected = actualMovie.id, actual = movieId)
        assertEquals(expected = actualMovie.title, actual = title)
        assertEquals(expected = actualMovie.voteAverage, actual = voteAverage)
        assertEquals(expected = actualMovie.releaseDate, actual = releaseDate)
        assertEquals(expected = actualMovie.posterUrl, actual = posterUrl)
        assertEquals(expected = actualMovie.isNowPlaying, actual = isNowPlaying)
        assertEquals(expected = actualMovie.isNowPopular, actual = isNowPopular)
        assertEquals(expected = actualMovie.isTopRated, actual = isTopRated)
        assertEquals(expected = actualMovie.isUpcoming, actual = isUpcoming)

        migratedDb.close()
        db.close()
    }

//    @Test
//    @Throws(IOException::class)
//    fun migrate2To3() {
//        val versionFrom = 2
//        val versionTo = 3
//
//        helper.createDatabase(TEST_DB, versionFrom).apply { close() }
//        helper.runMigrationsAndValidate(
//            TEST_DB,
//            versionTo,
//            true,
//            AppDb.MIGRATION_1_2,
//            AppDb.MIGRATION_2_3
//        ).close()
//    }

    private fun getMigratedRoomDatabase(): MovieDb {
        return MovieDb.getDbBuilder(
            context,
            TEST_DB
        )
            .addMigrations(MovieDbMigrations.MIGRATION_1_2)
            .build()
            .apply {
                openHelper.writableDatabase
                close()
            }
    }

    companion object {
        private const val TEST_DB = "migration-test.db"
    }
}
