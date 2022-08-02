package com.example.gitrepo.gitapi

import com.example.gitrepo.model.GitResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApiService {

    @GET("search/repositories")
    suspend fun getRepos(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int
    ): GitResponse
}