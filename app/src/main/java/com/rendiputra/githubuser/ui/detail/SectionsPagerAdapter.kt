package com.rendiputra.githubuser.ui.detail

import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    var username: String? = null

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = FollowFragment()
        fragment.arguments = bundleOf(
            FollowFragment.EXTRA_USERNAME to username,
            FollowFragment.EXTRA_POSITION to position
        )

        return fragment
    }
}