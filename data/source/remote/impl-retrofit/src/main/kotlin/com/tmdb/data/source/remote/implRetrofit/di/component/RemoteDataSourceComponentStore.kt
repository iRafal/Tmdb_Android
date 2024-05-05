package com.tmdb.data.source.remote.implRetrofit.di.component

import com.tmdb.data.api.implRetrofit.di.component.ApiComponentStore

class RemoteDataSourceComponentStore {
    private var _component: RemoteDataSourceComponent? = null

    private val apiComponentStore: ApiComponentStore by lazy { ApiComponentStore() }

    val component: RemoteDataSourceComponent
        get() {
            if (_component == null) {
                _component = DaggerRemoteDataSourceComponent.builder()
                    .apiInjections(apiComponentStore.component)
                    .build()
            }
            return checkNotNull(_component)
        }

    fun clean() {
        _component = null
        apiComponentStore.clean()
    }
}
