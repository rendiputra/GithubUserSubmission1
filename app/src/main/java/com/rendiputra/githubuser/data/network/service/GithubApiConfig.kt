package com.rendiputra.githubuser.data.network.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GithubApiConfig {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(GithubApiService::class.java)
}