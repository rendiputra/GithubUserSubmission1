package com.rendiputra.githubuser.data.network.service

import com.rendiputra.githubuser.data.network.response.DetailUserResponse
import com.rendiputra.githubuser.data.network.response.SearchUserResponse
import com.rendiputra.githubuser.data.network.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApiService {
    @GET("users")
    suspend fun getListUsers(@Header("Authorization") token : String) : List<UserResponse>

    @GET("users/{username}")
    suspend fun getDetailUser(
        @Path("username") username: String,
        @Header("Authorization") authorization: String
    ) : DetailUserResponse

    @GET("search/users")
    suspend fun searchUser(
        @Query("q") q: String,
        @Header("Authorization") authorization: String
    ) : SearchUserResponse

    @GET("users/{username}/followers")
    suspend fun getFollowerUser(
        @Path("username") username: String,
        @Header("Authorization") authorization: String
    ) : List<UserResponse>

    @GET("users/{username}/following")
    suspend fun getFollowingUser(
        @Path("username") username: String,
        @Header("Authorization") authorization: String
    ) : List<UserResponse>


}