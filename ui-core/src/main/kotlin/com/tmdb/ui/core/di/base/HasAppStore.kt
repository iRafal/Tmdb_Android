package com.tmdb.ui.core.di.base

import com.tmdb.store.AppStore


interface HasAppStore {
    val appStore: AppStore
}
