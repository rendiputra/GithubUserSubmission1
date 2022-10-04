package com.rendiputra.githubuser.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.rendiputra.githubuser.R
import com.rendiputra.githubuser.adapter.ListUserAdapter
import com.rendiputra.githubuser.data.Response
import com.rendiputra.githubuser.databinding.ActivitySearchBinding
import com.rendiputra.githubuser.di.DI
import com.rendiputra.githubuser.domain.User
import com.rendiputra.githubuser.ui.ViewModelFactory
import com.rendiputra.githubuser.ui.detail.DetailActivity

class SearchActivity : AppCompatActivity(), Toolbar.OnMenuItemClickListener,
    ListUserAdapter.OnItemClickCallback {

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
        listUserAdapter.setOnItemClickCallback(this)
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
                searchUser()
                true
            }
            else -> false
        }
    }

    override fun onItemClicked(data: User) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("extra_user", data)
        startActivity(intent)
    }
}