package com.rendiputra.githubuser.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rendiputra.githubuser.data.GithubDatabaseRepository
import com.rendiputra.githubuser.data.GithubRepository
import com.rendiputra.githubuser.data.Response
import com.rendiputra.githubuser.domain.DetailUser
import com.rendiputra.githubuser.domain.User
import kotlinx.coroutines.launch

class DetailViewModel(
    private val githubRepository: GithubRepository,
    private val githubDatabaseRepository: GithubDatabaseRepository
) : ViewModel() {

    private val _detailUser: MutableLiveData<Response<DetailUser>> = MutableLiveData()
    val detailUser: LiveData<Response<DetailUser>> get() = _detailUser

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> get() = _isFavorite

    fun getDetailUser(username: String, token: String) {
        viewModelScope.launch {
            githubRepository.getDetailUser(username, token).collect { response ->
                _detailUser.postValue(response)
            }
        }
    }

    fun isFavorite(login: String) {
        viewModelScope.launch {
            githubDatabaseRepository.isFavorite(login).collect {
                _isFavorite.postValue(it)
            }
        }
    }

    fun toggleFavoriteUser(user: User) {
        viewModelScope.launch {
            if (isFavorite.value == true) {
                githubDatabaseRepository.deleteUser(user)
            } else {
                githubDatabaseRepository.insertUser(user)
            }
        }
    }

}