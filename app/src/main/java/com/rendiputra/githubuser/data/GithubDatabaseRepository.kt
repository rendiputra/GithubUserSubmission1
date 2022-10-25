package com.rendiputra.githubuser.data

import com.rendiputra.githubuser.data.local.dao.UserDao
import com.rendiputra.githubuser.data.local.entity.asDomain
import com.rendiputra.githubuser.domain.User
import com.rendiputra.githubuser.domain.asEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GithubDatabaseRepository(private val userDao: UserDao) {

    fun getFavoriteUser(): Flow<List<User>> = userDao.getFavoriteUser().map {
        it.asDomain()
    }

    fun isFavorite(login: String) = userDao.isFavorite(login)

    suspend fun insertUser(user: User) {
        userDao.insertUser(user.asEntity())
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user.login)
    }
}