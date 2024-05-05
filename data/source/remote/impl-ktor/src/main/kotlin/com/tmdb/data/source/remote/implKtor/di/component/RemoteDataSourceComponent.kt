package com.tmdb.data.source.remote.implKtor.di.component

import com.tmdb.data.api.implKtor.di.component.HasApiServices
import com.tmdb.data.source.remote.implKtor.di.module.RemoteDataSourceModule
import com.tmdb.util.di.qualifiers.ApplicationScope
import dagger.Component

@ApplicationScope
@Component(modules = [RemoteDataSourceModule::class], dependencies = [HasApiServices::class])
interface RemoteDataSourceComponent: HasRemoteDataSources {
    @Component.Builder
    interface Builder {
        fun apiInjections(dependencies: HasApiServices): Builder
        fun build(): RemoteDataSourceComponent
    }
}
