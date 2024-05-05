package com.tmdb.feature.home.ui.di


object TestAppComponentStore {
    private var _component: TestAppComponent? = null
    val component: TestAppComponent
        get() {
            if (_component == null) {
                _component = DaggerTestAppComponent.builder().build()
            }
            return checkNotNull(_component)
        }

    fun clean() {
        _component = null
    }
}
