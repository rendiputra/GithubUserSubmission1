package com.rendiputra.githubuser.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rendiputra.githubuser.data.GithubRepository
import com.rendiputra.githubuser.data.Response
import com.rendiputra.githubuser.domain.DetailUser
import kotlinx.coroutines.launch

class DetailViewModel(private val githubRepository: GithubRepository) : ViewModel() {
    private val _detailUser: MutableLiveData<Response<DetailUser>> = MutableLiveData()
    val detailUser: LiveData<Response<DetailUser>> get() = _detailUser

    fun getDetailUser(username: String, token: String) {
        viewModelScope.launch {
            githubRepository.getDetailUser(username, token).collect { response ->
                _detailUser.postValue(response)
            }
        }
    }
}