package com.rendiputra.githubuser.domain

import android.os.Parcelable
import com.rendiputra.githubuser.data.local.entity.UserEntity
import kotlinx.parcelize.Parcelize


@Parcelize
data class User(
    val avatarUrl: String,
    val login: String,
    val url: String
) : Parcelable

fun User.asEntity(): UserEntity =
    UserEntity(
        login = login,
        avatarUrl = avatarUrl,
        url = url
    )