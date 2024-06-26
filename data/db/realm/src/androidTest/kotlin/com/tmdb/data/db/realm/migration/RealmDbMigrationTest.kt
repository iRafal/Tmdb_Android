package com.tmdb.data.db.realm.migration

import android.content.Context
import androidx.test.espresso.internal.inject.InstrumentationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tmdb.data.db.realm.MoviesRealmDbMigrations
import com.tmdb.data.db.realm.di.MoviesRealmDbConfig
import com.tmdb.data.db.realm.di.component.app.TestAppComponentStore
import com.tmdb.data.db.realm.di.component.db.TestDbComponent
import com.tmdb.data.db.realm.di.module.DispatchersTestModule
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.exceptions.RealmMigrationNeededException
import java.io.IOException
import javax.inject.Inject
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.runner.RunWith


@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class RealmDbMigrationTest {
    @Inject
    @DispatchersTestModule.DispatcherTestUnconfined
    lateinit var dispatcher: TestDispatcher

    @Inject
    @InstrumentationContext
    lateinit var context: Context

    private lateinit var testDbComponent: TestDbComponent

    @BeforeTest
    fun setup() {
        testDbComponent = TestAppComponentStore.component.testDbComponentBuilder.build()
        testDbComponent.inject(this)
        Realm.init(context)
        Dispatchers.setMain(dispatcher)
    }

    @AfterTest
    @Throws(IOException::class)
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun createDbAndVerifyAllSchemas() {
        val config = RealmConfiguration.Builder()
            .name(MoviesRealmDbConfig.REALM_DB_NAME)
            .migration(MoviesRealmDbMigrations.migration0To1())
            .schemaVersion(1)
            .build()

        Realm.getInstance(config).apply {
            checkV1Schema()
            checkV2Schema()
            close()
        }
        Realm.deleteRealm(config)
    }

    @Test(expected = RealmMigrationNeededException::class)
    @Throws(RealmMigrationNeededException::class)
    fun openDbV1AssetAsV2WithoutMigration() {
        val config = RealmConfiguration.Builder()
            .name(MoviesRealmDbConfig.REALM_DB_NAME)
            .assetFile(REALM_V1_DB_ASSET_NAME)
            .schemaVersion(2)
            .build()

        try {
            Realm.getInstance(config)
        } catch (e: RealmMigrationNeededException) {
            Realm.deleteRealm(config)
            throw e
        }
    }

    @Test
    fun migrationDbV1AssetToVersion2() {
        val config: RealmConfiguration = RealmConfiguration.Builder()
            .name(MoviesRealmDbConfig.REALM_DB_NAME)
            .assetFile(REALM_V1_DB_ASSET_NAME)
            .migration(MoviesRealmDbMigrations.migration1To2())
            .schemaVersion(2)
            .build()

        Realm.getInstance(config).apply {
            checkV1Schema()
            checkV2Schema()
            close()
        }
        Realm.deleteRealm(config)
    }

    companion object {
        // realm db file is stored at (f.e. on emulator): "~/data/data/package_name/files/
        const val REALM_V1_DB_ASSET_NAME = "movies-db-realm-v1.realm"
    }
}
