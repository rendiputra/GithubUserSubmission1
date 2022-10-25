package com.rendiputra.githubuser.ui.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
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

class DetailActivity : AppCompatActivity(), Toolbar.OnMenuItemClickListener {

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

        const val EXTRA_USER = "extra_user"
    }

    private val detailViewModel: DetailViewModel by viewModels {
        ViewModelFactory(DI.provideRepository(), DI.provideDatabaseRepository(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = intent.getParcelableExtra<User>(EXTRA_USER) as User

        if (savedInstanceState == null) {
            detailViewModel.getDetailUser(user.login, "token ${BuildConfig.API_KEY}")
            detailViewModel.isFavorite(user.login)
        }

        sectionsPagerAdapter = SectionsPagerAdapter(this)
        sectionsPagerAdapter.username = user.login
        binding.vpFollow.adapter = sectionsPagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.vpFollow) { tab, position ->
            tab.text = getString(TAB_TITLES[position])
        }.attach()



        setupToolbar()
        observeDetailUser()
        observeIsFavoriteUser()
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        binding.toolbar.setOnMenuItemClickListener(this)
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
                is Response.Loading -> {
                    toggleLoading(true)
                }
                is Response.Success -> {
                    toggleLoading(false)
                    setupDetailView(response.data)
                    setupItemDetailRecyclerView(response.data)
                }
                is Response.Error -> {
                    toggleLoading(false)
                }
            }
        }
    }

    private fun observeIsFavoriteUser() {
        detailViewModel.isFavorite.observe(this) { isFavorite ->
            val favoriteIcon =
                if (isFavorite) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24
            binding.toolbar.menu.getItem(0).setIcon(favoriteIcon)
        }
    }


    private fun toggleLoading(state: Boolean) {
        binding.collapsingToolbarLayout.visibility = if (state) View.GONE else View.VISIBLE
        binding.shimmerDetail.root.visibility = if (state) View.VISIBLE else View.GONE

        if (state) {
            binding.shimmerDetail.root.startShimmer()
        } else {
            binding.shimmerDetail.root.stopShimmer()
        }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.action_toggle_favorite -> {
                detailViewModel.toggleFavoriteUser(user)
                true
            }
            else -> false
        }
    }
}