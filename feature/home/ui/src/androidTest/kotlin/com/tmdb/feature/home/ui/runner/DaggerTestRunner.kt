package com.tmdb.feature.home.ui.runner

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.tmdb.feature.home.ui.DaggerTestApplication

class DaggerTestRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, name: String?, context: Context?): Application {
        return super.newApplication(cl, DaggerTestApplication::class.java.name, context)
    }
}
