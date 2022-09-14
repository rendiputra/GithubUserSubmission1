package com.rendiputra.githubuser.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rendiputra.githubuser.R
import com.rendiputra.githubuser.data.DetailItem

class ListDetailAdapter(private val listDetailItem: ArrayList<DetailItem>) : RecyclerView.Adapter<ListDetailAdapter.ListViewHolder>()  {

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivIcon: ImageView = itemView.findViewById(R.id.iv_icon)
        var tvItem: TextView = itemView.findViewById(R.id.tv_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_detail, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val itemDetail = listDetailItem[position]
        holder.ivIcon.setImageResource(itemDetail.icon)
        holder.tvItem.text = itemDetail.title
    }

    override fun getItemCount(): Int = listDetailItem.size
}