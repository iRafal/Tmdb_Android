package com.tmdb.data.api.config.url.provider.person

interface PersonUrlProvider {
    fun personDetailsUrl(personId: Int): String
}
