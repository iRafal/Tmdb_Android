package com.tmdb.data.source.remote.contract.person

import com.tmdb.data.model.PersonDataModel
import com.tmdb.data.model.state.DataState

interface PersonRemoteDataSource {
    suspend fun personDetails(
        personId: Int,
        language: String? = null,
        appendToResponse: String? = null
    ): DataState<PersonDataModel>
}
