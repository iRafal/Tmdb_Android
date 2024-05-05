package com.tmdb.data.api.implRetrofit.di.component

class ApiComponentStore {
    private var _component: ApiComponent? = null
    val component: ApiComponent
        get() {
            if (_component == null) {
                _component = DaggerApiComponent.builder().build()
            }
            return checkNotNull(_component)
        }

    fun clean() {
        _component = null
    }
}
