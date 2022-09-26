package com.rendiputra.githubuser.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rendiputra.githubuser.data.GithubRepository
import com.rendiputra.githubuser.di.DI
import com.rendiputra.githubuser.ui.main.MainViewModel

class ViewModelFactory(private val githubRepository: GithubRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) ->
                MainViewModel(DI.provideRepository()) as T
            else -> throw IllegalArgumentException("unregisted view model : ${modelClass.name}")
        }
    }

}