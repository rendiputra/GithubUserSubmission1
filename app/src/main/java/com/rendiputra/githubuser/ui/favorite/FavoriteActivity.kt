package com.rendiputra.githubuser.ui.favorite

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.rendiputra.githubuser.adapter.ListUserAdapter
import com.rendiputra.githubuser.databinding.ActivityFavoriteBinding
import com.rendiputra.githubuser.di.DI
import com.rendiputra.githubuser.domain.User
import com.rendiputra.githubuser.ui.ViewModelFactory
import com.rendiputra.githubuser.ui.detail.DetailActivity

class FavoriteActivity : AppCompatActivity(), ListUserAdapter.OnItemClickCallback,
    Toolbar.OnMenuItemClickListener {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var listUserAdapter: ListUserAdapter

    private var menuFavorite: Menu? = null

    private val favoriteViewModel: FavoriteViewModel by viewModels {
        ViewModelFactory(githubDatabaseRepository = DI.provideDatabaseRepository(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setupToolbar()
        showRecyclerList()
        observeListUser()
    }

    private fun showRecyclerList() {
        binding.rvUserFavorite.layoutManager =
            if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                GridLayoutManager(this, 2)
            } else {
                LinearLayoutManager(this)
            }

        listUserAdapter = ListUserAdapter()
        binding.rvUserFavorite.adapter = listUserAdapter

        listUserAdapter.setOnItemClickCallback(this)
    }

    override fun onItemClicked(data: User) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_USER, data)
        startActivity(intent)
    }

    private fun setupToolbar() {
        binding.toolbar.apply {
            setOnMenuItemClickListener(this@FavoriteActivity)
            setNavigationOnClickListener {
                onBackPressed()
            }
        }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return true
    }

    private fun observeListUser() {
        favoriteViewModel.listFavoriteUsers.observe(this) { listUserFavorite ->
            listUserAdapter.submitList(listUserFavorite)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menu?.getItem(0)?.setIcon(R.drawable.ic_baseline_favorite_24)
        menuFavorite = menu
        return super.onCreateOptionsMenu(menu)
    }
}