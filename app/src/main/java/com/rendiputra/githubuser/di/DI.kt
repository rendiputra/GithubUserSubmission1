package com.rendiputra.githubuser.di

import com.rendiputra.githubuser.data.GithubRepository
import com.rendiputra.githubuser.data.network.service.GithubApiConfig
import com.rendiputra.githubuser.data.network.service.GithubApiService

object DI {
    fun provideRepository(): GithubRepository {
        return GithubRepository(provideGithubApiService())
    }

    private fun provideGithubApiService(): GithubApiService {
        return GithubApiConfig.service
    }
}