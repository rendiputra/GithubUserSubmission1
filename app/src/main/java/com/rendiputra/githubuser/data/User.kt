package com.rendiputra.githubuser.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class User(
    var username: String,
    var company: String,
    var avatar: Int,
    var name: String,
    var location: String,
    var repository: String,
    var followers: String,
    var following: String

) : Parcelable
