package com.rendiputra.githubuser.data.network.response

import com.google.gson.annotations.SerializedName
import com.rendiputra.githubuser.domain.DetailUser

data class DetailUserResponse(

    @field:SerializedName("followers")
    val followers: Int? = null,

    @field:SerializedName("avatar_url")
    val avatarUrl: String? = null,

    @field:SerializedName("following_url")
    val followingUrl: String? = null,

    @field:SerializedName("following")
    val following: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("company")
    val company: String? = null,

    @field:SerializedName("location")
    val location: String? = null,

    @field:SerializedName("public_repos")
    val publicRepos: Int? = null,

    @field:SerializedName("login")
    val login: String? = null,

    @field:SerializedName("followers_url")
    val followersUrl: String? = null,

    @field:SerializedName("url")
    val url: String? = null
)

fun DetailUserResponse.asDomain(): DetailUser =
    DetailUser(
        followers = followers ?: 0,
        avatarUrl = avatarUrl ?: "",
        followingUrl = followingUrl ?: "",
        following = following ?: 0,
        name = name ?: "",
        company = company ?: "",
        location = location ?: "",
        publicRepos = publicRepos ?: 0,
        login = login ?: "",
        followersUrl = followersUrl ?: "",
        url = url ?: ""
    )