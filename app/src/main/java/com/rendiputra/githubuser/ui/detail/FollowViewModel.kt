package com.rendiputra.githubuser.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rendiputra.githubuser.data.GithubRepository
import com.rendiputra.githubuser.data.Response
import com.rendiputra.githubuser.domain.User
import kotlinx.coroutines.launch

class FollowViewModel(private val githubRepository: GithubRepository) : ViewModel() {
    private val _followers = MutableLiveData<Response<List<User>>>()
    val followers: LiveData<Response<List<User>>> get() = _followers

    fun getFollowerUser(username: String, token: String) {
        viewModelScope.launch {
            githubRepository.getFollowerUser(username, token).collect { response ->
                _followers.postValue(response)
            }
        }
    }

    fun getFollowingUser(username: String, token: String) {
        viewModelScope.launch {
            githubRepository.getFollowingUser(username, token).collect { response ->
                _followers.postValue(response)
            }
        }
    }

}