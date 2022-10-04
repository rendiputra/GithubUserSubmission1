package com.rendiputra.githubuser.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rendiputra.githubuser.data.GithubRepository
import com.rendiputra.githubuser.ui.detail.DetailViewModel
import com.rendiputra.githubuser.ui.detail.FollowViewModel
import com.rendiputra.githubuser.ui.main.MainViewModel
import com.rendiputra.githubuser.ui.search.SearchViewModel

class ViewModelFactory(private val githubRepository: GithubRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) ->
                MainViewModel(githubRepository) as T
            modelClass.isAssignableFrom(SearchViewModel::class.java) ->
                SearchViewModel(githubRepository) as T
            modelClass.isAssignableFrom(DetailViewModel::class.java) ->
                DetailViewModel(githubRepository) as T
            modelClass.isAssignableFrom(FollowViewModel::class.java) ->
                FollowViewModel(githubRepository) as T
            else -> throw IllegalArgumentException("unregisted view model : ${modelClass.name}")
        }
    }

}