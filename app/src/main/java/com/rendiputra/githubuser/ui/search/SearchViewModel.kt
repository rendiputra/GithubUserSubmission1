package com.rendiputra.githubuser.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rendiputra.githubuser.data.GithubRepository
import com.rendiputra.githubuser.data.Response
import com.rendiputra.githubuser.domain.User
import kotlinx.coroutines.launch

class SearchViewModel(private val githubRepository: GithubRepository): ViewModel() {
    private val _listUser: MutableLiveData<Response<List<User>>> = MutableLiveData()
    val listUser: LiveData<Response<List<User>>> get() = _listUser

    fun searchUser(token: String, q: String) {
        viewModelScope.launch {
            githubRepository.searchUser(token, q).collect { response ->
                _listUser.postValue(response)
            }
        }
    }

}