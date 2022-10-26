package com.rendiputra.githubuser.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rendiputra.githubuser.data.DetailItem
import com.rendiputra.githubuser.databinding.ItemRowDetailBinding

class ListDetailAdapter(private val listDetailItem: ArrayList<DetailItem>) :
    RecyclerView.Adapter<ListDetailAdapter.ListViewHolder>() {

    class ListViewHolder(private val binding: ItemRowDetailBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(detailItem: DetailItem) {
            binding.apply {
                ivIcon.setImageResource(detailItem.icon)
                tvItem.text = detailItem.title
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val itemDetail = listDetailItem[position]
        holder.bind(itemDetail)
    }

    override fun getItemCount(): Int = listDetailItem.size
}