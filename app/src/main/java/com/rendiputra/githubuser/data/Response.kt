package com.rendiputra.githubuser.data

sealed interface Response<out T> {
    object Loading : Response<Nothing>
    data class Success<out T>(val data: T) : Response<T>
    data class Error(val error: Throwable) : Response<Nothing>
}