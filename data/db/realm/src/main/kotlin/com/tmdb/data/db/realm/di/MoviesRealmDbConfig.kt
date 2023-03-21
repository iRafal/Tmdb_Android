package com.tmdb.data.db.realm.di

import android.content.Context
import com.tmdb.data.db.realm.MoviesRealmDbMigrations
import io.realm.Realm
import io.realm.RealmConfiguration

object MoviesRealmDbConfig {
    fun initRealm(context: Context) {
        Realm.init(context)
    }

    private const val realmVersion = 2L
    const val REALM_DB_NAME = "movies-db-realm.realm"
    private const val REALM_DB_TEST_NAME = "movies-test-db.realm"

    fun realmConfig(): RealmConfiguration =
        RealmConfiguration.Builder()
            .name(REALM_DB_NAME)
            .schemaVersion(realmVersion)
            .migration(MoviesRealmDbMigrations.migration0To1())
            .migration(MoviesRealmDbMigrations.migration1To2())
            .build()

    fun realmTestConfig(): RealmConfiguration = RealmConfiguration.Builder()
        .inMemory()
        .name(REALM_DB_TEST_NAME)
        .schemaVersion(realmVersion)
        .inMemory()
        .deleteRealmIfMigrationNeeded()
        .build()
}