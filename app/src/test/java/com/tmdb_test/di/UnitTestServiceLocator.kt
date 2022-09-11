package com.tmdb_test.di

import kotlinx.serialization.json.Json

object UnitTestServiceLocator {
    val json = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
    }
}