package com.rendiputra.githubuser.ui.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import com.rendiputra.githubuser.R
import com.rendiputra.githubuser.adapter.ListUserAdapter
import com.rendiputra.githubuser.data.Response
import com.rendiputra.githubuser.databinding.ActivitySearchBinding
import com.rendiputra.githubuser.di.DI
import com.rendiputra.githubuser.ui.ViewModelFactory

class SearchActivity : AppCompatActivity(), Toolbar.OnMenuItemClickListener {

    private lateinit var binding: ActivitySearchBinding
    private lateinit var listUserAdapter: ListUserAdapter

    private val searchViewModel: SearchViewModel by viewModels {
        ViewModelFactory(DI.provideRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listUserAdapter = ListUserAdapter()

        setupToolbar()
        setupRecycleView()
        observeListUser()
    }

    private fun observeListUser() {
        searchViewModel.listUser.observe(this) { response ->
            when (response) {
                is Response.Loading -> {
                    binding.lottieLoading.visibility = View.VISIBLE
                }
                is Response.Success -> {
                    binding.lottieLoading.visibility = View.GONE
                    listUserAdapter.submitList(response.data)
                }
                is Response.Error -> {
                    binding.lottieLoading.visibility = View.GONE
                }
            }
        }
    }

    private fun setupRecycleView() {
        binding.rvUser.adapter = listUserAdapter
    }

    private fun setupToolbar() {
        binding.toolbar.apply {
            setOnMenuItemClickListener(this@SearchActivity)
            setNavigationOnClickListener {
                onBackPressed()
            }
        }
    }

    private fun searchUser() {
        val query = binding.tieSearch.text.toString().trim()
        if (query.isNotBlank()) {
            searchViewModel.searchUser("token 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", query)
        } else {
            binding.tieSearch.error = "This field is not be empty"
        }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.action_search ->{
                Log.d("TAG", "onMenuItemClick: udah diklik")
                searchUser()
                true
            }
            else -> false
        }
    }
}