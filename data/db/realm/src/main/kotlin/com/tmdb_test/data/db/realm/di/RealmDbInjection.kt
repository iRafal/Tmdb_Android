package com.tmdb_test.data.db.realm.di

import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration

object RealmDbInjection {
    fun init(context: Context) {
        Realm.init(context)
    }

    private const val realmVersion = 1L

    fun realmConfig(): RealmConfiguration =
        RealmConfiguration.Builder()
            .schemaVersion(realmVersion)
            .deleteRealmIfMigrationNeeded()
            .build()
}