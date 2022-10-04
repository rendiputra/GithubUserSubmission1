package com.rendiputra.githubuser.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.rendiputra.githubuser.adapter.ListUserAdapter
import com.rendiputra.githubuser.data.Response
import com.rendiputra.githubuser.databinding.FragmentFollowBinding
import com.rendiputra.githubuser.di.DI
import com.rendiputra.githubuser.ui.ViewModelFactory

class FollowFragment : Fragment() {

    companion object {
        const val EXTRA_USERNAME = "extra_user"
        const val EXTRA_POSITION = "extra_position"
    }

    private var _binding: FragmentFollowBinding? = null
    private val binding get() = _binding!!

    private val followViewModel: FollowViewModel by viewModels {
        ViewModelFactory(DI.provideRepository())
    }
    private lateinit var username: String
    private var position = 0
    private lateinit var listUserAdapter: ListUserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        username = arguments?.getString(EXTRA_USERNAME) as String
        position = arguments?.getInt(EXTRA_POSITION) as Int

        listUserAdapter = ListUserAdapter()

        if (position == 0) {
            followViewModel.getFollowerUser(username, "token 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")
        } else {
            followViewModel.getFollowingUser(username, "token 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")
        }

        setupRecyclerView()
        observeListFollower()
    }

    private fun observeListFollower() {
        followViewModel.followers.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Response.Loading -> {
                    binding.lottieLoading.visibility = View.VISIBLE
                }
                is Response.Success -> {
                    listUserAdapter.submitList(response.data)
                    binding.lottieLoading.visibility = View.GONE
                }
                is Response.Error -> {
                    binding.lottieLoading.visibility = View.GONE
                }
            }
        }
    }

    private fun setupRecyclerView() {

        binding.rvFollow.adapter = listUserAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}