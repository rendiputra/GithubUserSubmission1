package com.rendiputra.githubuser.data

import android.util.Log
import com.rendiputra.githubuser.data.network.response.asDomain
import com.rendiputra.githubuser.data.network.service.GithubApiService
import com.rendiputra.githubuser.domain.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class GithubRepository(private val githubApiService: GithubApiService) {
    fun getListUser(token : String) : Flow<Response<List<User>>> = flow {
        emit(Response.Loading)
        try {
            val result = githubApiService.getListUsers(token).asDomain()
            emit(Response.Success(result))
        } catch (exception : Exception) {
            emit(Response.Error(exception))
        }

    }

    fun searchUser(token: String, q: String): Flow<Response<List<User>>> = flow {
        emit(Response.Loading)
        try {
            val result = githubApiService.searchUser(q, token).results.asDomain()
            Log.d("TAG", "searchUser: result = $result")
            emit(Response.Success(result))
        } catch (exception: Exception) {
            emit(Response.Error(exception))
        }
    }

}