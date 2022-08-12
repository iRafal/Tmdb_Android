package com.tmdb_test.data.source.remote.genre

import com.tmdb_test.data.api.impl_retrofit.NetworkErrorModel
import com.tmdb_test.data.api.impl_retrofit.genre.GenreApi
import com.tmdb_test.data.api.model.genre.GenresList
import com.tmdb_test.data.api.util.ApiResponse

//import com.tmdb_test.data.api.impl.genre.GenreApi
//class GenreRemoteDataSourceImpl(private val api: GenreApi) : GenreRemoteDataSource {
//
//    override suspend fun genreMovieList(language: String?): ApiResponse<List<Genre>> {
//        return api.genreMovieList(language)
//    }
//
//    override suspend fun genreTvList(language: String?): ApiResponse<List<Genre>> {
//        return api.genreTvList(language)
//    }
//}

class GenreRemoteDataSourceImpl(private val api: GenreApi) : GenreRemoteDataSource {

    override suspend fun genreMovieList(language: String?): ApiResponse<GenresList, NetworkErrorModel> {
        return api.genreMovieList(language)
    }

    override suspend fun genreTvList(language: String?): ApiResponse<GenresList, NetworkErrorModel> {
        return api.genreTvList(language)
    }
}