package com.example.search00

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubRepositoryService {
    @GET("users/{user}/repos")
    fun fetchReposList(@Path("user") user: String, @Query("sort") sort: String): Call<List<Repos>>
}