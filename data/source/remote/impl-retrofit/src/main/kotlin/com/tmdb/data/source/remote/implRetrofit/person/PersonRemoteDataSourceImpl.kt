package com.tmdb.data.source.remote.implRetrofit.person

import com.tmdb.data.api.implRetrofit.person.PersonApi
import com.tmdb.data.model.PersonDataModel
import com.tmdb.data.model.state.DataState
import com.tmdb.data.source.remote.contract.person.PersonRemoteDataSource
import com.tmdb.data.source.remote.implRetrofit.mapping.PersonApiModelToDataStateModelMapper

class PersonRemoteDataSourceImpl(
    private val api: PersonApi,
    private val personApiModelToDataStateModelMapper: PersonApiModelToDataStateModelMapper
) : PersonRemoteDataSource {
    override suspend fun personDetails(
        personId: Int,
        language: String?,
        appendToResponse: String?
    ): DataState<PersonDataModel> {
        return personApiModelToDataStateModelMapper.map(api.personDetails(personId, language, appendToResponse))
    }
}
