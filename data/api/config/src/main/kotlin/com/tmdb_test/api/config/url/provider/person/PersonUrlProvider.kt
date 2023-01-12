package com.tmdb_test.api.config.url.provider.person

interface PersonUrlProvider {
    fun personDetailsUrl(personId: Int): String
}