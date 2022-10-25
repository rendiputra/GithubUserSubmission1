package com.rendiputra.githubuser.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rendiputra.githubuser.data.GithubDatabaseRepository
import com.rendiputra.githubuser.domain.User
import kotlinx.coroutines.launch

class FavoriteViewModel(private val githubDatabaseRepository: GithubDatabaseRepository): ViewModel() {

    private val _listFavoriteUsers: MutableLiveData<List<User>> = MutableLiveData()
    val listFavoriteUsers: LiveData<List<User>> get() =_listFavoriteUsers

    init {
        viewModelScope.launch {
            githubDatabaseRepository.getFavoriteUser().collect {
                _listFavoriteUsers.postValue(it)
            }
        }
    }

}