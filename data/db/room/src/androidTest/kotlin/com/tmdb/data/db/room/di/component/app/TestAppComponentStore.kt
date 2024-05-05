package com.tmdb.data.db.room.di.component.app

object TestAppComponentStore {
    private var _component: TestAppComponent? = null
    val component: TestAppComponent
        get() = checkNotNull(_component)

    fun init() {
        if (_component != null) return
        _component = DaggerTestAppComponent.builder().build()
    }

    fun clean() {
        _component = null
    }
}
