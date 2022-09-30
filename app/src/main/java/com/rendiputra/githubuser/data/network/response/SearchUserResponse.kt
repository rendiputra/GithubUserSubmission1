package com.rendiputra.githubuser.data.network.response

import com.google.gson.annotations.SerializedName

data class SearchUserResponse(
    @field:SerializedName("items")
    val results: List<UserResponse>
)
