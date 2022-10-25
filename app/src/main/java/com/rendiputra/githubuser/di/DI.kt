package com.rendiputra.githubuser.di

import android.content.Context
import com.rendiputra.githubuser.data.GithubDatabaseRepository
import com.rendiputra.githubuser.data.GithubRepository
import com.rendiputra.githubuser.data.local.dao.UserDao
import com.rendiputra.githubuser.data.local.database.GithubDatabase
import com.rendiputra.githubuser.data.network.service.GithubApiConfig
import com.rendiputra.githubuser.data.network.service.GithubApiService

object DI {
    fun provideRepository(): GithubRepository {
        return GithubRepository(provideGithubApiService())
    }

    fun provideDatabaseRepository(context: Context): GithubDatabaseRepository {
        return GithubDatabaseRepository(provideUserDao(context))

    }

    private fun provideGithubApiService(): GithubApiService {
        return GithubApiConfig.service
    }

    fun provideUserDao(context: Context): UserDao {
        return GithubDatabase.getInstance(context).userDao()
    }
}