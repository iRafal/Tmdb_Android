package com.tmdb.di.component.app

import android.content.Context
import com.tmdb.di.component.store.AppStoreComponentStore
import com.tmdb.store.AppStore
import com.tmdb.util.di.qualifiers.ApplicationContext

internal class AppComponentStore {
    private var _appComponent: AppComponent? = null
    val component: AppComponent
        get() = checkNotNull(_appComponent)

    val appStoreComponentStore: AppStoreComponentStore by lazy { AppStoreComponentStore() }

    fun init(@ApplicationContext context: Context) {
        if (_appComponent != null) return

        appStoreComponentStore.init(context)

        val dependencies = object : AppComponentDependencies {
            override val applicationContext: Context
                get() = context

            override val appStore: AppStore
                get() = appStoreComponentStore.component.appStore
        }

        _appComponent = DaggerAppComponent.builder()
            .dependencies(dependencies)
            .build()
    }

    fun clean() {
        _appComponent = null
        appStoreComponentStore.clean()
    }
}
