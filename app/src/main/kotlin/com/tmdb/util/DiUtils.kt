package com.tmdb.util

import android.content.Context
import com.tmdb.MovieApp
import com.tmdb.di.component.app.AppComponent
import com.tmdb.di.component.store.AppStoreComponent

val Context.appComponent: AppComponent
    get() {
        return if (this is MovieApp) {
            this.appComponent
        } else {
            (this.applicationContext as MovieApp).appComponent
        }
    }

val Context.appStoreComponent: AppStoreComponent
    get() {
        return if (this is MovieApp) {
            this.appStoreComponent
        } else {
            (this.applicationContext as MovieApp).appStoreComponent
        }
    }
