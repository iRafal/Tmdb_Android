package com.tmdb.data.source.remote.implKtor.mapping

import com.tmdb.api.model.genre.Genre
import com.tmdb.api.model.genre.GenresList
import com.tmdb.api.model.util.ApiResponse
import com.tmdb.api.model.util.NetworkErrorModel
import com.tmdb.data.model.GenreDataModel
import com.tmdb.data.model.state.DataState
import javax.inject.Inject

interface GenreApiModelToDataStateModelMapper {
    fun map(input: ApiResponse<Genre, NetworkErrorModel>): DataState<GenreDataModel>
}

class GenreApiModelToDataStateModelMapperImpl @Inject constructor(
    private val genreApiModelToDataModelMapper: GenreApiModelToDataModelMapper
) : GenreApiModelToDataStateModelMapper {
    override fun map(input: ApiResponse<Genre, NetworkErrorModel>): DataState<GenreDataModel> {
        return apiModelToDataStateMapperImpl(genreApiModelToDataModelMapper::map).invoke(input)
    }
}

interface GenreListApiModelToDataStateModelMapper {
    fun map(input: ApiResponse<GenresList, NetworkErrorModel>): DataState<List<GenreDataModel>>
}

class GenreListApiModelToDataStateModelMapperImpl @Inject constructor(
    private val genreApiModelToDataModelMapper: GenreApiModelToDataModelMapper
) : GenreListApiModelToDataStateModelMapper {
    override fun map(input: ApiResponse<GenresList, NetworkErrorModel>): DataState<List<GenreDataModel>> {
        return genreListToDataStateMapperImpl(genreApiModelToDataModelMapper).invoke(input)
    }
}

internal fun genreListToDataStateMapperImpl(
    genreApiModelToDataModelMapper: GenreApiModelToDataModelMapper
) = { input: ApiResponse<GenresList, NetworkErrorModel> ->
    val dataMapper: (GenresList) -> List<GenreDataModel> = { genreList ->
        genreList.genres.map(genreApiModelToDataModelMapper::map)
    }
    input.mapApiStateToDataState(dataMapper)
}

interface GenreApiModelToDataModelMapper {
    fun map(input: Genre): GenreDataModel
}

class GenreApiModelToDataModelMapperImpl @Inject constructor() : GenreApiModelToDataModelMapper {
    override fun map(input: Genre): GenreDataModel {
        //TODO
        return GenreDataModel()
    }
}
