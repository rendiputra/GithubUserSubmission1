package com.rendiputra.githubuser.data

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

}