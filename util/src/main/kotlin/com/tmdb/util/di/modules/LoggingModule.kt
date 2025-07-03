package com.tmdb.util.di.modules

import com.tmdb.util.logging.AppLogger
import com.tmdb.util.logging.impl.AppLoggerImpl
import org.koin.dsl.module

fun loggingModule() = module {
    factory<AppLogger> { AppLoggerImpl() }
}