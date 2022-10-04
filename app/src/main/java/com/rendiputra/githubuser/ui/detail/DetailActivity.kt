package com.rendiputra.githubuser.ui.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.rendiputra.githubuser.BuildConfig
import com.rendiputra.githubuser.R
import com.rendiputra.githubuser.adapter.ListDetailAdapter
import com.rendiputra.githubuser.data.DetailItem
import com.rendiputra.githubuser.data.Response
import com.rendiputra.githubuser.databinding.ActivityDetailBinding
import com.rendiputra.githubuser.di.DI
import com.rendiputra.githubuser.domain.DetailUser
import com.rendiputra.githubuser.domain.User
import com.rendiputra.githubuser.ui.ViewModelFactory

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private lateinit var user: User

    private lateinit var listDetailAdapter: ListDetailAdapter

    private lateinit var sectionsPagerAdapter: SectionsPagerAdapter

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.follower,
            R.string.following

        )
    }

    private val detailViewModel: DetailViewModel by viewModels {
        ViewModelFactory(DI.provideRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = intent.getParcelableExtra<User>("extra_user") as User
        sectionsPagerAdapter = SectionsPagerAdapter(this)
        sectionsPagerAdapter.username = user.login
        binding.vpFollow.adapter = sectionsPagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.vpFollow) { tab, position ->
            tab.text = getString(TAB_TITLES[position])
        }.attach()

        detailViewModel.getDetailUser(user.login, "token ${BuildConfig.API_KEY}")

        setupToolbar()
        observeDetailUser()
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setupDetailView(detailUser: DetailUser) {
        binding.tvName.text = detailUser.name
        binding.tvUsername.text = detailUser.login

        Glide.with(this)
            .load(detailUser.avatarUrl)
            .into(binding.ivAvatar)
    }

    private fun setupItemDetailRecyclerView(detailUser: DetailUser) {
        val listItems = arrayListOf<DetailItem>()
        listItems.add(
            DetailItem(
                R.drawable.ic_baseline_people_24,
                getString(R.string.format_followers, detailUser.followers)
            )
        )
        listItems.add(
            DetailItem(
                R.drawable.ic_baseline_following_24,
                getString(R.string.format_following, detailUser.following)
            )
        )
        listItems.add(DetailItem(R.drawable.ic_baseline_company_24, detailUser.company))
        listItems.add(
            DetailItem(
                R.drawable.ic_baseline_book_24,
                getString(R.string.format_repository, detailUser.publicRepos)
            )
        )
        listItems.add(DetailItem(R.drawable.ic_baseline_location_on_24, detailUser.location))

        listDetailAdapter = ListDetailAdapter(listItems)
        binding.rvDetailItem.adapter = listDetailAdapter
    }

    private fun observeDetailUser() {
        detailViewModel.detailUser.observe(this) { response ->
            when (response) {
                is Response.Loading -> {}
                is Response.Success -> {
                    setupDetailView(response.data)
                    setupItemDetailRecyclerView(response.data)
                }
                is Response.Error -> {}
            }
        }
    }
}