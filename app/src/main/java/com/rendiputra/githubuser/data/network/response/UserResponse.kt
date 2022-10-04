package com.rendiputra.githubuser.data.network.response

import com.google.gson.annotations.SerializedName
import com.rendiputra.githubuser.domain.User


data class UserResponse(

    @field:SerializedName("avatar_url")
    val avatarUrl: String? = null,

    @field:SerializedName("login")
    val login: String? = null,

    @field:SerializedName("url")
    val url: String? = null
)

fun UserResponse.asDomain(): User =
    User(
        avatarUrl = avatarUrl ?: "",
        login = login ?: "",
        url = url ?: ""
    )

fun List<UserResponse>?.asDomain(): List<User> {
    return this?.map { it.asDomain() } ?: emptyList()
}
