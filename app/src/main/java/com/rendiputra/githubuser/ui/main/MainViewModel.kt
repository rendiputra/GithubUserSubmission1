package com.rendiputra.githubuser.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rendiputra.githubuser.data.GithubRepository
import com.rendiputra.githubuser.data.Response
import com.rendiputra.githubuser.domain.User
import kotlinx.coroutines.launch

class MainViewModel(private val githubRepository: GithubRepository) : ViewModel() {
    private val _listUser: MutableLiveData<Response<List<User>>> = MutableLiveData()
    val listUser: LiveData<Response<List<User>>> get() = _listUser

    fun getListUser(token: String) {
        viewModelScope.launch {
            githubRepository.getListUser(token).collect {
                _listUser.postValue(it)
            }
        }

    }
}