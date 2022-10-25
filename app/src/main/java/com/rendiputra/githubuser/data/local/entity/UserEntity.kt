package com.rendiputra.githubuser.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rendiputra.githubuser.data.network.response.UserResponse
import com.rendiputra.githubuser.domain.User

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey
    val login: String,
    val avatarUrl: String,
    val url: String
)

fun UserEntity.asDomain(): User =
    User(
        avatarUrl = avatarUrl,
        login = login,
        url = url
    )

fun List<UserEntity>.asDomain(): List<User> = map { it.asDomain() }