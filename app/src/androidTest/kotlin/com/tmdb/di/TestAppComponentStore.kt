package com.tmdb.di

import com.tmdb.di.component.store.AppStoreComponentStore


object TestAppComponentStore {
    private var _component: TestAppComponent? = null

    val component: TestAppComponent
        get() = checkNotNull(_component)

    private val appStoreComponentStore: AppStoreComponentStore by lazy { AppStoreComponentStore() }

    fun init() {
        if (_component != null) return

        _component = DaggerTestAppComponent.builder().build()
        appStoreComponentStore.init(component.instrumentationContext)
    }

    fun clean() {
        _component = null
        appStoreComponentStore.clean()
    }
}
