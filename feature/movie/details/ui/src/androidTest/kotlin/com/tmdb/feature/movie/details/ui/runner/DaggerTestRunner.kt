package com.tmdb.feature.movie.details.ui.runner

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.tmdb.feature.movie.details.ui.DaggerTestApplication

class DaggerTestRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, name: String?, context: Context?): Application {
        return super.newApplication(cl, DaggerTestApplication::class.java.name, context)
    }
}
