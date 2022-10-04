package com.rendiputra.githubuser.ui.main

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.rendiputra.githubuser.R
import com.rendiputra.githubuser.adapter.ListUserAdapter
import com.rendiputra.githubuser.data.Response
import com.rendiputra.githubuser.databinding.ActivityMainBinding
import com.rendiputra.githubuser.di.DI
import com.rendiputra.githubuser.domain.User
import com.rendiputra.githubuser.ui.ViewModelFactory
import com.rendiputra.githubuser.ui.detail.DetailActivity
import com.rendiputra.githubuser.ui.search.SearchActivity

class MainActivity : AppCompatActivity(), ListUserAdapter.OnItemClickCallback,
    Toolbar.OnMenuItemClickListener {
    private lateinit var binding: ActivityMainBinding

    private lateinit var listUserAdapter: ListUserAdapter

    private val mainViewModel: MainViewModel by viewModels {
        ViewModelFactory(DI.provideRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        showRecyclerList()

        mainViewModel.getListUser("token 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")
        mainViewModel.listuser.observe(this) { response ->
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

    private fun showRecyclerList() {
        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvUsers.layoutManager = GridLayoutManager(this, 2)
        } else {
            binding.rvUsers.layoutManager = LinearLayoutManager(this)
        }
        listUserAdapter = ListUserAdapter()
        binding.rvUsers.adapter = listUserAdapter

        listUserAdapter.setOnItemClickCallback(this)
    }


    override fun onItemClicked(data: User) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("extra_user", data)
        startActivity(intent)
    }

    private fun setupToolbar() {
        binding.toolbar.apply {
            setOnMenuItemClickListener(this@MainActivity)
            setNavigationOnClickListener {
                onBackPressed()
            }
        }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.action_search -> {
                val intent = Intent(this, SearchActivity::class.java)
                startActivity(intent)
                true
            }
            else -> false
        }
    }

}