package com.tmdb_test.data.db.realm.di

import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration

object RealmDbInjection {
    fun init(context: Context) {
        Realm.init(context)
    }

    private const val realmVersion = 1L
    private const val REALM_DB_NAME = "movies-db-realm"
    private const val REALM_DB_TEST_NAME = "movies-test-db-realm"

    fun realmConfig(): RealmConfiguration =
        RealmConfiguration.Builder()
            .name(REALM_DB_NAME)
            .schemaVersion(realmVersion)
            .deleteRealmIfMigrationNeeded()
            .build()

    fun realmTestConfig(): RealmConfiguration = RealmConfiguration.Builder()
        .inMemory()
        .name(REALM_DB_TEST_NAME)
        .schemaVersion(realmVersion)
        .deleteRealmIfMigrationNeeded()
        .build()
}