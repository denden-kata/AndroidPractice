package com.example.search00

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {
    private const val BASE_URL = "https://api.github.com/"
    private const val ACCOUNT_NAME = "denden-kata"

    private fun restClient() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun fetchReposList() : Response<List<Repos>> {
        val service = restClient().create(GitHubRepositoryService::class.java)
        return service.fetchReposList(ACCOUNT_NAME, "desc").execute()
    }
}