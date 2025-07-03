package com.tmdb.data.source.remote.implRetrofit.mapping

import com.tmdb.api.model.data.DataPage
import com.tmdb.api.model.person.Person
import com.tmdb.api.model.util.ApiResponse
import com.tmdb.api.model.util.NetworkErrorModel
import com.tmdb.data.model.PersonDataModel
import com.tmdb.data.model.state.DataState

fun interface PersonApiModelToDataStateModelMapper {
    fun map(input: ApiResponse<Person, NetworkErrorModel>): DataState<PersonDataModel>
}

class PersonApiModelToDataStateModelMapperImpl(
    private val personApiModelToDataModelMapper: PersonApiModelToDataModelMapper
) : PersonApiModelToDataStateModelMapper {
    override fun map(input: ApiResponse<Person, NetworkErrorModel>): DataState<PersonDataModel> {
        return apiModelToDataStateMapperImpl(personApiModelToDataModelMapper::map).invoke(input)
    }
}

interface PersonListApiModelToDataStateModelMapper {
    fun map(input: ApiResponse<DataPage<Person>, NetworkErrorModel>): DataState<List<PersonDataModel>>
}

class PersonListApiModelToDataStateModelMapperImpl(
    private val personApiModelToDataModelMapper: PersonApiModelToDataModelMapper
) : PersonListApiModelToDataStateModelMapper {
    override fun map(input: ApiResponse<DataPage<Person>, NetworkErrorModel>): DataState<List<PersonDataModel>> {
        return apiModelListToDataStateMapperImpl(personApiModelToDataModelMapper::map).invoke(input)
    }
}

interface PersonApiModelToDataModelMapper {
    fun map(input: Person): PersonDataModel
}

class PersonApiModelToDataModelMapperImpl: PersonApiModelToDataModelMapper {
    override fun map(input: Person): PersonDataModel {
        //TODO
        return PersonDataModel()
    }
}
