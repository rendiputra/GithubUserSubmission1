package com.rendiputra.githubuser.domain


data class DetailUser(
    val followers: Int,
    val avatarUrl: String,
    val followingUrl: String,
    val following: Int,
    val name: String,
    val company: String,
    val location: String,
    val publicRepos: Int,
    val login: String,
    val followersUrl: String,
    val url: String
)
