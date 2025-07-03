package com.tmdb.di.module

import android.content.Context
import com.tmdb.data.source.local.implRoom.di.module.localDataSourceModule
import com.tmdb.data.source.remote.implKtor.di.module.remoteDataSourceModule
import com.tmdb.feature.home.ui.di.module.homeFeatureModule
import com.tmdb.feature.movie.details.ui.di.module.movieDetailsFeatureModule
import com.tmdb.store.di.module.app.appStoreModule
import com.tmdb.store.di.module.env.appNetworkModule
import com.tmdb.util.di.modules.loggingModule
import com.tmdb.worker.TestExampleWorker
import org.koin.androidx.workmanager.dsl.worker
import org.koin.dsl.module

fun appModule() = module {
    includes(
        uiModule(),
        appStoreModule(),
        loggingModule(),
        localDataSourceModule(),
        remoteDataSourceModule(),
        homeFeatureModule(),
        movieDetailsFeatureModule(),
    )

    worker { TestExampleWorker(get<Context>(), get(), get()) }
}
