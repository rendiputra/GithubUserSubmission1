package com.rendiputra.githubuser.data

import com.rendiputra.githubuser.data.network.response.asDomain
import com.rendiputra.githubuser.data.network.service.GithubApiService
import com.rendiputra.githubuser.domain.DetailUser
import com.rendiputra.githubuser.domain.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class GithubRepository(private val githubApiService: GithubApiService) {
    fun getListUser(token: String): Flow<Response<List<User>>> = flow {
        emit(Response.Loading)
        try {
            val result = githubApiService.getListUsers(token).asDomain()
            emit(Response.Success(result))
        } catch (exception: Exception) {
            emit(Response.Error(exception))
        }

    }

    fun getDetailUser(username: String, token: String): Flow<Response<DetailUser>> = flow {
        emit(Response.Loading)
        try {
            val result = githubApiService.getDetailUser(username, token).asDomain()
            emit(Response.Success(result))
        } catch (exception: Exception) {
            emit(Response.Error(exception))
        }
    }

    fun searchUser(token: String, q: String): Flow<Response<List<User>>> = flow {
        emit(Response.Loading)
        try {
            val result = githubApiService.searchUser(q, token).results.asDomain()
            emit(Response.Success(result))
        } catch (exception: Exception) {
            emit(Response.Error(exception))
        }
    }

    fun getFollowerUser(username: String, token: String): Flow<Response<List<User>>> = flow {
        emit(Response.Loading)
        try {
            val result = githubApiService.getFollowerUser(username, token).asDomain()
            emit(Response.Success(result))
        } catch (exception: Exception) {
            emit(Response.Error(exception))
        }
    }

    fun getFollowingUser(username: String, token: String): Flow<Response<List<User>>> = flow {
        emit(Response.Loading)
        try {
            val result = githubApiService.getFollowingUser(username, token).asDomain()
            emit(Response.Success(result))
        } catch (exception: Exception) {
            emit(Response.Error(exception))
        }
    }

}