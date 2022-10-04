package com.rendiputra.githubuser.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class User(
    val avatarUrl: String,
    val login: String,
    val url: String
) : Parcelable