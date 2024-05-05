package com.tmdb.data.db.realm.runner

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.tmdb.data.db.realm.DaggerTestApplication

class DaggerTestRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, name: String?, context: Context?): Application {
        return super.newApplication(cl, DaggerTestApplication::class.java.name, context)
    }
}
