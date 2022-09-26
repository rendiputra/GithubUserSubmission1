package com.rendiputra.githubuser.data.network.service

import com.rendiputra.githubuser.data.network.response.DetailUserResponse
import com.rendiputra.githubuser.data.network.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface GithubApiService {
    @GET("users")
    suspend fun getListUsers(@Header("Authorization") token : String) : List<UserResponse>

    @GET("users/{username}")
    suspend fun getDetailUser(
        @Path("username") username: String,
        @Header("Authorization") authorization: String
    ) : DetailUserResponse
}