package com.tmdb_test.data.db.realm.di.modules

import android.content.Context
import com.tmdb_test.data.db.realm.di.RealmDbInjection
import com.tmdb_test.data.db.realm.movie.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RealmDbModule {
    @Provides
    @Singleton
    fun dataBase(
        @ApplicationContext applicationContext: Context,
        realmConfig: RealmConfiguration
    ): Realm {
        RealmDbInjection.init(applicationContext)
        return Realm.getInstance(realmConfig)
    }

    @Singleton
    @Provides
    fun providesRealmConfig(): RealmConfiguration =
        RealmDbInjection.realmConfig()
}