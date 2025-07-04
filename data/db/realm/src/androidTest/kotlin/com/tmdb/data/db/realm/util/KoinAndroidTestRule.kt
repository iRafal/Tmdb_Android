package com.tmdb.data.db.realm.util

import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.koin.core.context.GlobalContext.getKoinApplicationOrNull
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module

class KoinAndroidTestRule(private val modules: List<Module> = emptyList()) : TestWatcher() {
    override fun starting(description: Description) {
        if (getKoinApplicationOrNull() == null) {
            startKoin {
                modules(modules)
            }
        } else {
            loadKoinModules(modules)
        }
    }

    override fun finished(description: Description) {
        unloadKoinModules(modules)
    }
}
