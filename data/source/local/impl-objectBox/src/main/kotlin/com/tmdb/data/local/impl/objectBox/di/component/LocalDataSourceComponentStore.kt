package com.tmdb.data.local.impl.objectBox.di.component

import android.content.Context
import com.tmdb.util.di.qualifiers.ApplicationContext

object LocalDataSourceComponentStore {
    private var _component: LocalDataSourceComponent? = null
    val component: LocalDataSourceComponent
        get() = checkNotNull(_component)

    fun init(@ApplicationContext applicationContext: Context) {
        if (_component != null) return
        _component = DaggerLocalDataSourceComponent.builder().appContext(applicationContext).build()
    }

    fun clean() {
        _component = null
    }
}
