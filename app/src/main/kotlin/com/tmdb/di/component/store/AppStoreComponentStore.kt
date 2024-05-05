package com.tmdb.di.component.store

import android.content.Context
import com.tmdb.data.source.local.contract.MovieLocalDataSource
import com.tmdb.data.source.local.implRoom.di.component.LocalDataSourceComponentStore
import com.tmdb.data.source.remote.contract.discover.DiscoverRemoteDataSource
import com.tmdb.data.source.remote.contract.genre.GenreRemoteDataSource
import com.tmdb.data.source.remote.contract.movie.MovieRemoteDataSource
import com.tmdb.data.source.remote.contract.person.PersonRemoteDataSource
import com.tmdb.data.source.remote.implKtor.di.component.RemoteDataSourceComponentStore

internal class AppStoreComponentStore {

    private var _component: AppStoreComponent? = null

    val component: AppStoreComponent
        get() = checkNotNull(_component)

    private val remoteSourceComponentStore: RemoteDataSourceComponentStore by lazy { RemoteDataSourceComponentStore() }
    private val localSourceComponentStore: LocalDataSourceComponentStore by lazy { LocalDataSourceComponentStore() }

    fun init(applicationContext: Context) {
        if (_component != null) return

        localSourceComponentStore.init(applicationContext)

        val dependencies = object : AppStoreComponent.Dependencies {
            override val discoverRemoteDataSource: DiscoverRemoteDataSource
                get() = remoteSourceComponentStore.component.discoverRemoteDataSource
            override val genreRemoteDataSource: GenreRemoteDataSource
                get() = remoteSourceComponentStore.component.genreRemoteDataSource
            override val movieRemoteDataSource: MovieRemoteDataSource
                get() = remoteSourceComponentStore.component.movieRemoteDataSource
            override val personRemoteDataSource: PersonRemoteDataSource
                get() = remoteSourceComponentStore.component.personRemoteDataSource
            override val movieLocalDataSource: MovieLocalDataSource
                get() = localSourceComponentStore.component.movieLocalDataSource
        }

        _component = DaggerAppStoreComponent.builder()
            .appContext(applicationContext)
            .dependencies(dependencies)
            .build()
    }

    fun clean() {
        _component = null
        remoteSourceComponentStore.clean()
        localSourceComponentStore.clean()
    }
}
