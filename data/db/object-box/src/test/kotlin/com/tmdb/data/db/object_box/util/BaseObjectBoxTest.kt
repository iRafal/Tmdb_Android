package com.tmdb.data.db.object_box.util

import com.tmdb.data.db.object_box.di.UnitTestServiceLocator
import com.tmdb.data.db.object_box.movie.MyObjectBox
import io.objectbox.BoxStore
import io.objectbox.DebugFlags
import java.io.File
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseObjectBoxTest {
    private val dispatcher = UnitTestServiceLocator.textDispatcher
    protected lateinit var store: BoxStore

    @Before
    open fun setUp() {
        BoxStore.deleteAllFiles(TEST_DIRECTORY)
        store = MyObjectBox.builder()
            .directory(TEST_DIRECTORY)
            .debugFlags(DebugFlags.LOG_QUERIES or DebugFlags.LOG_QUERY_PARAMETERS)
            .build()
        Dispatchers.setMain(dispatcher)
    }

    @After
    open fun tearDown() {
        store.close()
        BoxStore.deleteAllFiles(TEST_DIRECTORY)
        Dispatchers.resetMain()
    }

    companion object {
        private val TEST_DIRECTORY = File("objectbox-example/test-db")
    }
}