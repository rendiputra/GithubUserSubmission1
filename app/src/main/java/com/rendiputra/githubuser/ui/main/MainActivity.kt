package com.rendiputra.githubuser.ui.main

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rendiputra.githubuser.DetailActivity
import com.rendiputra.githubuser.R
import com.rendiputra.githubuser.adapter.ListUserAdapter
import com.rendiputra.githubuser.data.Response
import com.rendiputra.githubuser.di.DI
import com.rendiputra.githubuser.domain.User
import com.rendiputra.githubuser.ui.ViewModelFactory

class MainActivity : AppCompatActivity(), ListUserAdapter.OnItemClickCallback {
    private lateinit var rvUsers: RecyclerView

    private lateinit var listUserAdapter : ListUserAdapter

    private val mainViewModel : MainViewModel by viewModels {
        ViewModelFactory(DI.provideRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvUsers = findViewById(R.id.rv_heroes)
        rvUsers.setHasFixedSize(true)

        showRecyclerList()

        mainViewModel.getListUser("token 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")
        mainViewModel.listuser.observe(this) { response ->
            when (response) {
                is Response.Loading -> {
                    Log.d("TAG", "onCreate: loading")
                }
                is Response.Success -> {
                    listUserAdapter.submitList(response.data)
                }
                is Response.Error -> {
                    Log.d("TAG", "onCreate: error = ${response.error.message}")
                }
            }
        }
    }

    private fun showRecyclerList() {
        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rvUsers.layoutManager = GridLayoutManager(this, 2)
        } else {
            rvUsers.layoutManager = LinearLayoutManager(this)
        }
        listUserAdapter = ListUserAdapter()
        rvUsers.adapter = listUserAdapter

        listUserAdapter.setOnItemClickCallback(this)
    }


    override fun onItemClicked(data: User) {
        val intent = Intent(this, DetailActivity::class.java)
//        intent.putExtra("extra_user", data)
        startActivity(intent)
    }

}