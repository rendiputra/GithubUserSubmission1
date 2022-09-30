package com.rendiputra.githubuser.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rendiputra.githubuser.databinding.ItemRowUserBinding
import com.rendiputra.githubuser.domain.User

class ListUserAdapter() : ListAdapter<User, ListUserAdapter.ListViewHolder>(DiffCallback)  {

    private companion object DiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.login == newItem.login
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListViewHolder(private val binding: ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.tvItemName.text = user.login
            Glide.with(binding.ivAvatar)
                .load(user.avatarUrl)
                .into(binding.ivAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: User)
    }
}