package com.tmdb.data.db.realm.di.modules

import android.content.Context
import com.tmdb.data.db.realm.di.MoviesRealmDbConfig
import com.tmdb.data.db.realm.movie.dao.MovieDao
import com.tmdb.data.db.realm.movie.dao.MovieDaoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import io.realm.Realm
import io.realm.RealmConfiguration


@Module
object DbModule {

    @Provides
    fun providesRealmConfig(
        @ApplicationContext applicationContext: Context
    ): RealmConfiguration {
        MoviesRealmDbConfig.initRealm(applicationContext)
        return MoviesRealmDbConfig.realmConfig()
    }

    @Provides
    fun dataBase(realmConfig: RealmConfiguration): Realm = Realm.getInstance(realmConfig)

    @Provides
    fun movieDao(impl: MovieDaoImpl): MovieDao = impl
}
